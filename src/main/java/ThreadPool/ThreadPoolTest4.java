package ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wuhao on 16/10/14.
 */
public class ThreadPoolTest4 implements Runnable{


    @Override
    public void run() {
        System.out.println("当前线程的名称是:"+Thread.currentThread().getName());

        synchronized (this){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        ArrayBlockingQueue<Runnable> queue=new ArrayBlockingQueue<Runnable>(4);

        ThreadPoolExecutor executor=new ThreadPoolExecutor(2,6,1, TimeUnit.DAYS,queue);

        for (int i = 0; i < 10; i++) {
            executor.execute(new Thread(new ThreadPoolTest4(),"".concat(""+i)));
            /**
             * 开始执行线程的是核心线程 意味着队列里还没有请求 当请求数达到核心线程数 那么开始将新的请求
             * 放到请求队列中 当请求队列达到阀值时 那么接下来的请求会在不达到线程池的最大数时 线程池新建
             * 线程来处理这些请求 如果达到最大值 那么会抛出异常已经不会再接受任务了
             */
            int threadSize=queue.size();
            System.out.println("当前线程的大小是:"+threadSize);

            if (threadSize == 4) {
                boolean flag=queue.offer(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("我是新线程能否加入队列?");
                    }
                });
                System.out.println("是否可以加入新线程:"+flag);
            }
        }
        executor.shutdown();
    }
}
