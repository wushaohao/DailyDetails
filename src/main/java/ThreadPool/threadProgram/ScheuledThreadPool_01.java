package ThreadPool.threadProgram;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by wuhao on 17/4/17.
 */
public class ScheuledThreadPool_01 {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService= Executors.newScheduledThreadPool(5);

        for (int i = 0; i < 3; i++) {
            final String taskName=String.valueOf(i);

            /**
             * scheduleAtFixedRate该方法设置了执行周期，下一次执行时间相当于是上一次的执行时间加上period，它是采用已固定的频率来执行任务
             * scheduleWithFixedRate该方法设置了执行周期，与scheduleAtFixedRate方法不同的是，下一次执行时间是上一次任务执行完的系统时间加上period，因而具体执行时间不是固定的，但周期是固定的，是采用相对固定的延迟来执行任务
             */
            scheduledExecutorService.scheduleAtFixedRate(new task(i),0,5, TimeUnit.SECONDS);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Shutting down executor...");

        scheduledExecutorService.shutdown();


        try {
            boolean isDone=scheduledExecutorService.awaitTermination(1,TimeUnit.DAYS);
            if (!isDone){
                System.out.println("finished all tasks...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static class task implements Runnable{
        private String taskName;
        public task(int i){
            this.taskName=String.valueOf(i);
        }

        @Override
        public void run() {
            System.out.println("Thread "+ taskName +" is start,startTime is "+new Date());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread "+ taskName +" is finished,finishTime is "+new Date());
        }
    }
}



