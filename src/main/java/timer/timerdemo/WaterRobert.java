package timer.timerdemo;

import timer.TimeUtil;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author:wuhao
 * @description:注水机器人
 * @date:18/9/8
 */
public class WaterRobert extends TimerTask {
    private int bucketCapacity;

    private Timer timer;

    public WaterRobert(Timer inTimer) {
        this.timer = inTimer;
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        System.out.println("Current time is:" + TimeUtil.getCurrentTime());

        if (bucketCapacity < 5) {
            bucketCapacity++;
            System.out.println("current buck has water:" + bucketCapacity + "L");
        } else {
            System.out.println("cancel waterTimer is:" + TimeUtil.getCurrentTime());
            System.out.println("current cancelTask is:" + timer.purge());
            cancel();
            System.out.println("current cancelTask is:" + timer.purge());
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM:dd HH:mm:ss");
            System.out.println("cancel waterTimer succeed is:" + sdf.format(scheduledExecutionTime()));
            System.out.println("current buck has water:" + bucketCapacity + "L");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("allCancel waterTimer succeed is:" + sdf.format(scheduledExecutionTime()));
            System.out.println("all cancelTask begin is:" + timer.purge());
            timer.cancel();
            //timer.cancel() 队列中执行的Task全部被清空
            System.out.println("all cancelTask end is:" + timer.purge());
        }

    }
}
