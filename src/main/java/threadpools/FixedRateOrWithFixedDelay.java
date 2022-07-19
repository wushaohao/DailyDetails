package threadpools;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author:wuhao
 * @description:
 * @date:18/10/18
 */
public class FixedRateOrWithFixedDelay {
    static class MyRunnable implements Runnable {
        int period = 1;

        @Override
        public void run() {
            System.out.println("---------------第 " + period + " 周期-------------");
            //秒
            System.out.println("begin = " + System.currentTimeMillis() / 1000);
            //任务执行时间
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end =   " + System.currentTimeMillis() / 1000);
            period++;
        }
    }

    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        //作为一个周期任务提交,period 为1000ms,任务执行时间为2000ms任务的周期执行是连着的,没有间隔时间。这是因为任务的运行时间大于周期执行时间，即当任务还没结束时，周期时间已经到了，所以任务刚结束，就可以进行下一周期的执行。
//        pool.scheduleAtFixedRate(new MyRunnable(), 50, 1000, TimeUnit.MILLISECONDS);
        //间隔一个固定的时间执行的，无论任务的执行时间是否大于周期时间。
        pool.scheduleWithFixedDelay(new MyRunnable(), 50, 1000, TimeUnit.MILLISECONDS);
//        pool.shutdown();
    }
}
