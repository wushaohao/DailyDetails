package ThreadPool.threadProgram;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wuhao on 17/4/16.
 */
public class countdownlunch_01 {
    public static void main(String[] args) {

        final CountDownLatch countDownLatch=new CountDownLatch(3);

        for (char i = 'A'; i <='C'; i++) {
            System.out.println("this is "+i);
            final char finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(String.valueOf(finalI)+"is running...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(String.valueOf(finalI)+"is finished...");

                    countDownLatch.countDown();
                }
            }).start();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                    System.out.println("其他线程已经执行结束,我开始了...");
                    System.out.println("I am D is running....");
                    Thread.sleep(1000);
                    System.out.println("I am D is finished....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
