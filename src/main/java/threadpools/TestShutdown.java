package threadpools;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author:wuhao
 * @description:
 * @date:18/10/18
 */
public class TestShutdown {
    /**
     * void setExecuteExistingDelayedTasksAfterShutdownPolicy(boolean value)：
     * 在调用线程池调用了 shutdown（）方法后，是否继续执行现有延时任务（就是通过 schedule()方法提交的延时任务 ）的策略;默认值为false；在以下两种种的情况下，延时任务将会被终止：
     * void setContinueExistingPeriodicTasksAfterShutdownPolicy(boolean value)
     * 在调用线程池调用了 shutdown（）方法后，是否继续执行现有周期任务（通过 scheduleAtFixedRate、scheduleWithFixedDelay 提交的周期任务）的策略；默认值为false；在以下两种的情况下，周期任务将会被终止：
     * 获取这个两个策略的设置值：
     * boolean getContinueExistingPeriodicTasksAfterShutdownPolicy()：
     * 取有关在此执行程序已 shutdown 的情况下、是否继续执行现有定期任务的策略。
     * boolean getExecuteExistingDelayedTasksAfterShutdownPolicy()：
     * 获取有关在此执行程序已 shutdown 的情况下是否继续执行现有延迟任务的策略
     *
     * @param args
     */
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(1);
        //shutdown时，周期任务的策略
        pool.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
        pool.scheduleWithFixedDelay(new MyRunnable(), 50, 1000, TimeUnit.MILLISECONDS);

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pool.shutdown();
    }

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
}
