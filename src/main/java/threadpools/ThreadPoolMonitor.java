package threadpools;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author:wuhao
 * @description:线程池监控属性
 * @date:18/11/2
 */
@Slf4j
public class ThreadPoolMonitor {

    /**
     * 创建线程池
     */
    private static ExecutorService pool = new ThreadPoolExecutor(50, 100, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(10000), new ThreadFactory() {
        private AtomicLong THREAD_COUNT = new AtomicLong(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread();
            thread.setName("ThreadPool-Monitor-Demo" + THREAD_COUNT.getAndIncrement());
            thread.setDaemon(true);
            return thread;
        }
    }, new ThreadPoolExecutor.AbortPolicy());

    private static final int COUNT = 10000;

    public static void main(String[] args) {
        for (int i = 0; i < COUNT; i++) {
//            pool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("执行任务");
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });

            pool.execute(() -> {
                System.out.print("执行--->" + 1);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }

        ThreadPoolExecutor executor = ((ThreadPoolExecutor) pool);

        while (true) {
            System.out.println("-------------");
            int queueSize = executor.getQueue().size();
            System.out.println("当前排队线程数：" + queueSize);

            int activeCount = executor.getActiveCount();
            System.out.println("当前活动线程数:" + activeCount);

            long completedTaskCount = executor.getCompletedTaskCount();
            System.out.println("执行完成线程数:" + completedTaskCount);

            long taskCount = executor.getTaskCount();
            System.out.println("总线程数:" + taskCount);

            int largePoolSize = executor.getLargestPoolSize();
            System.out.println("最大线程数是:" + largePoolSize);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
