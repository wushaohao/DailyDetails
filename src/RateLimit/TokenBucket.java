package RateLimit;

import com.google.common.base.Preconditions;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:wuhao
 * @Description:令牌桶(限流)
 * @Date:17/9/13
 */
public class TokenBucket implements LifeCycle{
    // 默认桶大小个数 即最大瞬间流量是64
    private static final int  DEFAULT_BUCKET_SIZE = 1024*1024*64;
    // 一个桶的单位是1字节
    private int everyTokenSize = 1;
    // 瞬间最大流量
    private int maxFlowRate;
    // 平均流量
    private int avgFlowRate;

    /**
     * 队列来缓存桶数量:最大流量峰值＝everyTokenSize*DEFAULT_BUCKET_SIZE   64M=1*1024*1024*64
     * ArrayBlockingQueue是一个由数组支持的有界阻塞队列。此队列按 FIFO（先进先出）原则对元素进行排序。队列的头部 是在队列中存在时间最长的元素
     */
    private ArrayBlockingQueue<Byte> tokenQueue = new ArrayBlockingQueue<Byte>(DEFAULT_BUCKET_SIZE);

    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private volatile boolean isStarted =false;

    private ReentrantLock lock = new ReentrantLock(true);

    private static final byte A_CHAR = 'a';

    public TokenBucket() {
    }

    public TokenBucket(int maxFlowRate, int avgFlowRate) {
        this.maxFlowRate = maxFlowRate;
        this.avgFlowRate = avgFlowRate;
    }

    public TokenBucket(int everyTokenSize, int maxFlowRate, int avgFlowRate) {
        this.everyTokenSize = everyTokenSize;
        this.maxFlowRate = maxFlowRate;
        this.avgFlowRate = avgFlowRate;
    }

    public void addToken(Integer tokenNum){
        /**
         * 若桶已经满了 就不再加入新的令牌
         * offer方法是加锁的，在当前的Array的count等于数组的容量时，也就是数组满的时候返回false，如果没满，那么插入数据到Array中，最后解锁返回true
         * add方法其实也是使用了offer方法，但是不同的是，如果数组是满的那么add会抛出IllegalStateException异常，add成功后会返回true
         * put的实现里用到了Condition—notFull，在put的时候，如果数组已经满了，那么添加元素是不成功的（offer的实现），但是此时如果希望能等待数组有空间添加元素，那么可以使用put，
         * 如果数据已满，那么在notFull上等待。如果有数组的元素移除的操作就会唤醒这个put，让元素能添加到数组中。同时在调用insert方法是会调用notEmpty.signal() 唤醒在notEmpty上等待的线程
         */
        for (int i = 0; i < tokenNum; i++) {
            tokenQueue.offer(Byte.valueOf(A_CHAR));
        }
    }

    /**
     * 获取足够令牌数
     * @param dataSize
     * @return
     */
    public boolean getTokens(byte[] dataSize){
        Preconditions.checkNotNull(dataSize);
        Preconditions.checkArgument(isStarted,"please invoke start method first!!!");
        // 传输内容大小对应的令牌个数
        int needTokenNum = dataSize.length / everyTokenSize +1;
        final ReentrantLock lock =this.lock;
        lock.lock();
        try {
            // 是否存在足够的令牌数量
            boolean result = needTokenNum<=tokenQueue.size();
            if (!result){
                return false;
            }
            int tokenCount = 0;
            for (int i = 0; i < needTokenNum; i++) {
                /**
                 * poll方法是从数组中弹出一个元素，但是如果当前的数组内没有元素则直接返回null，如果有元素，
                 * 那么返回指定的索引位置的元素，并删除原来的元素。同时在弹出元素后唤醒等待在notFull上的线程
                 * take:如果我们希望在获取元素的时候，如果没有元素，我们希望线程阻塞指导有元素可取。那么这个实现就是take方法了。
                 * 在take时，如果当前的数组内没有元素的话，那么线程等待在notEmpty上，直到insert是唤醒在notEmpty上等待的线程
                 * peek方法是比较简单的，只是在数组存在元素的时候，获取指定的索引的元素，但是不会移除这个元素
                 */
                Byte poll = tokenQueue.poll();
                if (poll !=null){
                    tokenCount++;
                }
            }
            // 获取的令牌数是否等于需要的令牌数
            return tokenCount == needTokenNum;
        }finally {
            lock.unlock();
        }

    }

    public TokenBucket build(){
        start();
        return this;
    }

    @Override
    public void start() {
        // 初始化令牌桶队列大小
        if (maxFlowRate!=0){
            tokenQueue = new ArrayBlockingQueue<Byte>(maxFlowRate);
        }

        // 初始化令牌生产者 向桶里添加令牌
        TokenProducer tokenProducer = new TokenProducer(avgFlowRate,this);
        scheduledExecutorService.scheduleAtFixedRate(tokenProducer, 0,1, TimeUnit.SECONDS);

        isStarted = true;
    }

    @Override
    public boolean isStarted() {
        return isStarted;
    }

    @Override
    public void stop() {
        isStarted = false;
        scheduledExecutorService.shutdown();
    }

    class TokenProducer implements Runnable{
        private int avgFlowRate;
        private TokenBucket tokenBucket;

        public TokenProducer(int avgFlowRate, TokenBucket tokenBucket) {
            this.avgFlowRate = avgFlowRate;
            this.tokenBucket = tokenBucket;
        }

        @Override
        public void run() {
            tokenBucket.addToken(avgFlowRate);
        }
    }

    public static TokenBucket newBuilder(){
        return new TokenBucket();
    }

    public TokenBucket everyTokenSize(int everyTokenSize){
        this.everyTokenSize = everyTokenSize;
        return this;
    }

    public TokenBucket maxFlowRate(int maxFlowRate) {
        this.maxFlowRate = maxFlowRate;
        return this;
    }

    public TokenBucket avgFlowRate(int avgFlowRate) {
        this.avgFlowRate = avgFlowRate;
        return this;
    }

    private String stringCopy(String data,int copyNum){
        StringBuilder sbulider = new StringBuilder(data.length()*copyNum);
        for (int i = 0; i < copyNum; i++) {
            sbulider.append(data);
        }
        return sbulider.toString();
    }

    public static void main(String[] args) throws Exception{
        tokenTest();
    }

    private static void arrayTest(){
        ArrayBlockingQueue<Integer> tokenQueue = new ArrayBlockingQueue<Integer>(10);
        tokenQueue.offer(1);
        tokenQueue.offer(1);
        tokenQueue.offer(1);
        System.out.println(tokenQueue.size());
        System.out.println(tokenQueue.remainingCapacity());
    }

    /**
     * 测试令牌桶
     * @throws InterruptedException
     * @throws IOException
     */
    private static void tokenTest() throws InterruptedException,IOException{
        TokenBucket tokenBucket =TokenBucket.newBuilder().avgFlowRate(512).maxFlowRate(1024).build();

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("")));
        String data="xxxx";// 4个字节
        for (int i = 0; i < 1000; i++) {
            Random random = new Random();
            int i1=random.nextInt();
            boolean tokens = tokenBucket.getTokens(tokenBucket.stringCopy(data,i1).getBytes());
            TimeUnit.MILLISECONDS.sleep(100);
            if (tokens){
                bufferedWriter.write("token pass --- index:"+i1);
                System.out.println("token pass --- index:" + i1);
            }else{
                bufferedWriter.write("token rejuect --- index" + i1);
                System.out.println("token rejuect --- index" + i1);
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        bufferedWriter.close();
    }
}
