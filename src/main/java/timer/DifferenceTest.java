package timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author:wuhao
 * @description:schedule与scheduleAtFixedRate区别
 * @date:18/9/8
 */
public class DifferenceTest {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("current time is:" + sdf.format(calendar.getTime()));

        //设置成6s前的时间
        calendar.add(Calendar.SECOND, -6);

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 打印当前的计划执行时间
                System.out.println("Scheduled exec time is:" + sdf.format(scheduledExecutionTime()));
                System.out.println("task is being executed");
            }
        }, calendar.getTime(), 2000);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // 打印当前的计划执行时间
                System.out.println("Scheduled exec time is:" + sdf.format(scheduledExecutionTime()));
                System.out.println("task is being executed");
            }
        }, calendar.getTime(), 2000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 打印当前的计划执行时间
                System.out.println("Scheduled exec time is:" + sdf.format(scheduledExecutionTime()));
                System.out.println("task is being executed");
            }
        }, calendar.getTime(), 2000);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 打印当前的计划执行时间
                System.out.println("Scheduled exec time is:" + sdf.format(scheduledExecutionTime()));
                System.out.println("task is being executed");
            }
        }, calendar.getTime(), 2000);
    }
}
