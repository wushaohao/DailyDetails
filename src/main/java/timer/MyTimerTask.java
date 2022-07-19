package timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;

/**
 * @author:wuhao
 * @description:Task类
 * @date:18/9/8
 */
public class MyTimerTask extends TimerTask {
    private String name;
    private int count = 0;

    public MyTimerTask(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        if (count < 3) {
            // 以yyyy-MM-dd HH:mm:ss形式打印出当前时间
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("Current date is:" + sdf.format(calendar.getTime()));
            // 具体业务处理
            System.out.println("Current exc name is:" + name);
        } else {
            cancel();
            System.out.println("TimeTask cancel...");
        }


    }
}
