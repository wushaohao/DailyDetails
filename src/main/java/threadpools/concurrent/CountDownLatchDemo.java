package threadpools.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author:wuhao
 * @description:countDownLatch
 * @date:18/5/31
 */
public class CountDownLatchDemo {
    private static final CountDownLatch latch = new CountDownLatch(2);
    static int a = 0;
    static int b = 0;

    public static void main(String[] args) {
        final Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                a = 2;
                System.out.println("线程A运行开始");
                try {
                    Thread.sleep(3000);
                    System.out.println("线程A运行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        });

        final Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                b = 3;
                System.out.println("线程B运行开始");
                try {
                    Thread.sleep(3000);
                    System.out.println("线程B运行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        });

        threadA.start();
        threadB.start();

        System.out.println("等待2个线程执行结束");
        try {
            System.out.println(Thread.currentThread().getName() + "开始等待..");
            latch.await();
            System.out.println(Thread.currentThread().getName() + "结束等待,继续执行..");
            System.out.println("结果是:" + (a + b));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
