package ThreadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wuhao on 16/10/13.
 */
public class ThreadPoolTest implements Runnable{

    @Override
    public void run() {

        synchronized (this){
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(3000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void  main(String[] args){

        BlockingQueue<Runnable> queue=new LinkedBlockingDeque<Runnable>();
        ThreadPoolExecutor executor=new ThreadPoolExecutor(2,6,1, TimeUnit.DAYS,queue);
        for (int i=0;i<10;i++){
            executor.execute(new Thread(new ThreadPoolTest(),"TestThread".concat(""+i)));
            int threadSize=queue.size();
            System.out.println("线程队列的大小是:"+threadSize);
        }
        executor.shutdown();

    }
}
