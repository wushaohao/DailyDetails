package Semaphore;

import createthreadpool.CreateThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import static java.util.concurrent.Executors.*;

/**
 * @author:wuhao
 * @Description:线程信号量
 * @Date:17/11/14
 */
public class TestSemaphore {
    public static void main(String[] args) {
        // 创建线程池
        ExecutorService executorService = CreateThreadPool.createThreadPool(20);

        // 只能5个线程同时访问
        final Semaphore semaphore = new Semaphore(5);

        // 模拟20个客户端访问
        for (int i = 0; i < 20; i++) {
            final int NO = i;

            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        // 获取许可
                        semaphore.acquire();
                        System.out.println("Accessing:" + NO);
                        Thread.sleep((long) (Math.random() * 10000));

                        // 访问完后,释放
                        semaphore.release();
                        System.out.println("-----------" + semaphore.availablePermits());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
            executorService.execute(run);
        }
    }
}
