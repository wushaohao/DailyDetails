package ThreadPool.threadProgram;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by wuhao on 17/4/16.
 */
public class cyclicBarrier_01 {
    public static void main(String[] args) {
        final CyclicBarrier cyclicBarrier=new CyclicBarrier(3);

        Random random=new Random();
        for (char i = 'A'; i <= 'C'; i++) {
            final String name=String.valueOf(i);
            final long prepareTime= random.nextInt(1000);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(name+"is preparing.."+"wait"+prepareTime);
                    try {
                        Thread.sleep(prepareTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(name+"is prepared for others...");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                    System.out.println("all is ready,Go...");
                }
            }).start();


        }

    }
}
