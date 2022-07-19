package timer.timerdemo;

import java.text.SimpleDateFormat;
import java.util.TimerTask;

/**
 * @author:wuhao
 * @description:跳舞机器人
 * @date:18/9/8
 */
public class DanceRobert extends TimerTask {

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM:dd HH:mm:ss");
        System.out.println("DanceRobert is dancing:" + sdf.format(scheduledExecutionTime()));
    }
}
