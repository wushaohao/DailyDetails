package timer.timerdemo;

import timer.TimeUtil;

import java.util.Timer;

/**
 * @author:wuhao
 * @description:客户端测试类
 * @date:18/9/8
 */
public class ClientRobert {

    public static void main(String[] args) {
        Timer timer = new Timer();


        WaterRobert waterRobert = new WaterRobert(timer);
        DanceRobert danceRobert = new DanceRobert();

        timer.scheduleAtFixedRate(waterRobert, TimeUtil.getCurrentDate(), 2000);
        timer.scheduleAtFixedRate(danceRobert, TimeUtil.getCurrentDate(), 2000);

    }
}
