package Semaphore;

import createthreadpool.CreateThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

/**
 * @author:wuhao
 * @Description:线程池的创建不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式更加明确线程池的运行规则，规避资源耗尽的风险 1):newFixedThreadPool和newSingleThreadExecutor:主要问题是堆积的请求处理队列可能会耗费大量的内存 甚至是00M
 * 2):newCachedThreadPool和newScheduledThreadPool:主要问题是线程数最大数是Integer.MAX_VALUE,可能会创建数量非常多的线程，甚至是00M
 * @Date:17/11/16
 */
public class SemaphoreThread {
    private int a = 0;

    private static final int THREAD_COUNT = 20;

    class Bank {
        private int account = 100;

        public int getAccount() {
            return account;
        }

        public void save(int money) {
            account += money;
        }
    }

    class NewThread implements Runnable {
        private Bank bank;
        private Semaphore semaphore;

        public NewThread(Bank bank, Semaphore semaphore) {
            this.bank = bank;
            this.semaphore = semaphore;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            int b = a++;
            if (semaphore.availablePermits() > 0) {
                System.out.println("线程" + b + "启动，进入银行,有位置立即去存钱");
            } else {
                System.out.println("线程" + b + "启动，进入银行,无位置，去排队等待等待");
            }

            try {
                semaphore.acquire();
                bank.save(10);
                System.out.println(b + "账户余额为：" + bank.getAccount());
                Thread.sleep(1000);
                System.out.println("线程" + b + "存钱完毕，离开银行");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void userThread() {
        Bank bank = new Bank();
        // 定义10个新号量
        Semaphore semaphore = new Semaphore(10);
        // 建立一个缓存线程池
        ExecutorService es = CreateThreadPool.createThreadPool(THREAD_COUNT);

        // 建立20个线程
        for (int i = 0; i < THREAD_COUNT; i++) {
            // 执行一个线程池
            es.submit(new Thread(new NewThread(bank, semaphore)));
        }

        // 关闭线程池
        es.shutdown();

        // 从信号量中获取两个许可，并且在获得许可之前，一直将线程阻塞
        semaphore.acquireUninterruptibly(2);
        System.out.println("到点了，工作人员要吃饭了");
        // 释放2个许可，并将其返回给信号量
        semaphore.release(2);
    }

    public static void main(String[] args) {
        SemaphoreThread semaphoreThread = new SemaphoreThread();
        semaphoreThread.userThread();
    }
}
