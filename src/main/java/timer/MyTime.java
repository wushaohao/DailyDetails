package timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;

/**
 * @author:wuhao
 * @description:
 * @date:18/9/8
 */
public class MyTime {
    public static void main(String[] args) {
        // 创建一个Timer实例
        Timer timer = new Timer();
        // 创建一个MyTimerTask实例
        MyTimerTask myTimerTask = new MyTimerTask("Hulk");
        //通过创建的Timer实例定时定频率调用MyTimerTask的业务逻辑
        //第一次执行是在当前时间的两秒后,之后每个一秒钟执行一次
        //timer.schedule(myTimerTask, 2000, 1000);

        /**
         * 获取当前时间 并设置成距离当前时间三秒后的时间
         */
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current date is:" + sdf.format(calendar.getTime()));
        calendar.add(Calendar.SECOND, 3);

        //------------- schedule用法-----------
        /**
         * 1.在时间等于或超过time的时候执行且执行一次task
         */
        myTimerTask.setName("schedule1");
        timer.schedule(myTimerTask, calendar.getTime());


        /**
         * 2.在时间等于或超过time的时候首次执行,之后每隔period后重复执行
         */
        myTimerTask.setName("schedule2");
        timer.schedule(myTimerTask, calendar.getTime(), 2000);

        /**
         * 3.等待delay毫秒后执行且仅执行一次task
         */
        myTimerTask.setName("schedule3");
        timer.schedule(myTimerTask, 1000);

        /**
         * 4.等待delay毫秒后首次执行每隔period之后重复执行task
         */
        myTimerTask.setName("schedule4");
        timer.schedule(myTimerTask, 1000, 2000);


        //------------- scheduleAtFixedRate用法-----------
        /**
         * 1.
         */
        myTimerTask.setName("scheduleAtFixedRate2");
        timer.scheduleAtFixedRate(myTimerTask, calendar.getTime(), 2000);

        /**
         *
         */
        myTimerTask.setName("scheduleAtFixedRate2");
        timer.scheduleAtFixedRate(myTimerTask, 2000, 1000);
        // 最近发生此任务执行安排的时间
        System.out.println("schedule time is:" + sdf.format(myTimerTask.scheduledExecutionTime()));
    }
}
