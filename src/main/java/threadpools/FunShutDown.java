package threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author:wuhao
 * @description:线程池ShutDown方法
 * @date:18/5/26
 */
public class FunShutDown {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(1);

        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("run..");
                        Thread.sleep(3000);
                        System.out.println("==" + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        try {
            Thread.sleep(1000);
            //当我们调用shutdown后，线程池将不再接受新的任务，但也不会去强制终止已经提交或者正在执行中的任务
            pool.shutdown();
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("新增提交任务..");
                        Thread.sleep(30000);
                        System.out.println("--" + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
