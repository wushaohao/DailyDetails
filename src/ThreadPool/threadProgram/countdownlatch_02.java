package ThreadPool.threadProgram;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wuhao on 17/5/8.
 * CountDownLatch， 一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待
 *
 * CountDownLatch类只提供了一个构造器：
 * public CountDownLatch(int count) {};  //参数count为计数值
 *
 * public void await() throws InterruptedException { };   //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
   public boolean await(long timeout, TimeUnit unit) throws InterruptedException { };  //和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
   public void countDown() { };  //将count值减1
 */
public class countdownlatch_02 {

    static class readNum implements Runnable{
        private int id;
        private CountDownLatch latch;

        public readNum(int id,CountDownLatch latchs){
            this.id=id;
            this.latch=latchs;
        }

        @Override
        public void run() {
            synchronized (this){
                System.out.println("id:"+id);
                latch.countDown();
                System.out.println("Threads "+id+" end,other continue...");
            }

        }
    }
    public static void main(String[] args) {
        CountDownLatch latch=new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(new readNum(i,latch)).start();

        }

        try {
            latch.await();
            System.out.println("Thread is end...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
