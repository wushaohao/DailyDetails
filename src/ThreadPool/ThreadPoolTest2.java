package ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wuhao on 16/10/13.
 */
public class ThreadPoolTest2 implements Runnable{

    public static  void main(String[] args){

        ArrayBlockingQueue<Runnable> queue=new ArrayBlockingQueue<Runnable>(4);//长度固定为4的线程队列

        ThreadPoolExecutor executor=new ThreadPoolExecutor(2,6,1, TimeUnit.DAYS,queue);

        /*
        * 核心线程数是2
        * 线程池的大小是6
        * 队列大小是4
        * 总共有10个任务，那么在执行时，2个核心线程，会立即被执行，那么线程池还有4个线程可执行任务，那么还剩下4个任务请求则会放到
        * 请请求队列，如果超过这个队列的大小还有请求继续请求，那么直接抛出异常RejectedExecuationException。
        * */
        for (int i = 0; i <10 ; i++) {
            executor.execute(new Thread(new ThreadPoolTest2(),"TestThread".concat(""+i)));
            int threadSize=queue.size();
            System.out.println("线程队列大小－－>"+i);
        }

        executor.shutdown();

    }

    @Override
    public void run() {
        synchronized (this){
            System.out.println("当前线程的名称是:"+Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
