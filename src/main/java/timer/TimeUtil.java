package timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author:wuhao
 * @description:获取当前时间工具类
 * @date:18/9/8
 */
public class TimeUtil {

    public static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String currentTime = sdf.format(calendar.getTime());
        return currentTime;
    }


    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return calendar.getTime();
    }

}
