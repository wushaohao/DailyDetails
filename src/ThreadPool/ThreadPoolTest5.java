package ThreadPool;

import java.util.concurrent.*;

/**
 * Created by wuhao on 16/10/14.
 */
public class ThreadPoolTest5 implements Runnable{
    @Override
    public void run() {
        System.out.println("当前的线程名称是:"+Thread.currentThread().getName());
        synchronized (this){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Runnable> queue=new ArrayBlockingQueue<Runnable>(4);
        //ThreadPoolExecutor executor=new ThreadPoolExecutor(2,6,1, TimeUnit.DAYS,queue);

        ExecutorService executor = Executors.newFixedThreadPool(6);

        //运行10个线程
        for (int i = 0; i <10 ; i++) {
            executor.execute(new Thread(new ThreadPoolTest5(),"".concat(""+i)));
            int threadSize=queue.size();
            System.out.println("当前线程队列的大小是:"+threadSize);
            if (threadSize == 4) {
                queue.put(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("我是新线程,能进入队列中!");
                    }
                });
            }
        }

        executor.shutdown();
    }
}
