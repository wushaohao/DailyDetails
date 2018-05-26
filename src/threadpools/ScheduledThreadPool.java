package threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author:wuhao
 * @description:调度线程池
 * @date:18/5/26
 */
public class ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);
        // 延迟10s执行任务
//        for (int i = 0; i < 10; i++) {
//            pool.schedule(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread().getName()+"上车了..");
//                }
//            }, 10, TimeUnit.SECONDS);
//        }

        // 周期性执行任务
        for (int i = 0; i < 10; i++) {
            pool.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"发车了..");
                }
            }, 1, 1, TimeUnit.SECONDS);
        }
    }
}
