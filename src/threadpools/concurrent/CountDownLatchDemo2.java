package threadpools.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author:wuhao
 * @description:测试demo--哪个线程调用了await(),那么哪个线程等待调用了countDown()的线程的计数为0后继续执行
 * @date:18/7/22
 */
public class CountDownLatchDemo2 {
    public static void main(String[] args) {
        int a = 0;

        final CountDownLatch latch = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            a = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"is running");
                    latch.countDown();
                }
            }).start();
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+"开始调用等待...");
                    latch.await();
                    System.out.println(Thread.currentThread().getName()+"等待结束了 到我执行了...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println(">>>>值是:" + a);
        System.out.println(Thread.currentThread().getName()+",I am done...");
        System.out.println("----值是:" + a);
    }
}
