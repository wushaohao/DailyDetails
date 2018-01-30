package Semaphore;

import createthreadpool.CreateThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author:wuhao
 * @Description:信号量流控测试类
 * @Date:17/11/21
 */
public class SemaphoreTest {
    private final static int  COUNT = 30;

    private static ExecutorService executorService = CreateThreadPool.createThreadPool(COUNT);
    /**
     * 手动创建线程池
     */

    private final static int SE_COUNT = 10;

    private static Semaphore semaphore = new Semaphore(SE_COUNT);

    public static void main(String[] args) {

        for (int i = 0; i < COUNT; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 获取信号量
                        semaphore.acquire();
                        System.out.println("获取信号量!!!");
                        // 释放信号量
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        // 关闭线程池
        executorService.shutdown();
    }
}
