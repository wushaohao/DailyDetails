package time;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author:wuhao
 * @Description:时间转换公共类
 * @Date:17/10/10
 */
public class CsfTimeUtil {

    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 根据指定的日期获取月的第一天的时间
     *
     * @param date
     * @return
     */
    public static String getDateOfMonthFirstDay(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.DAY_OF_MONTH, 1);
        rightNow.set(Calendar.HOUR_OF_DAY, 0);
        rightNow.set(Calendar.MILLISECOND, 0);
        rightNow.set(Calendar.SECOND, 0);
        rightNow.set(Calendar.MINUTE, 0);
        rightNow.set(Calendar.MONTH, rightNow.get(Calendar.MONTH));
        String monthFirst = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rightNow.getTime());
        return monthFirst;
    }

    /**
     * 获取当前时间的时间戳
     * @return
     */
    public static long getBeginDate() {
        Date date = new Date();
        return date.getTime();
    }

    /**
     * 获取当月月初的时间戳
     * @return
     * @throws Exception
     */
    public static long getEndDate() throws Exception {
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String monthHead = getDateOfMonthFirstDay(new Date());
        Date date = format.parse(monthHead);
        return date.getTime();
    }

    public static void main(String[] args) throws Exception{
        System.out.println(getCurrentTime());
        System.out.println(getDateOfMonthFirstDay(new Date()));

        System.out.println(getBeginDate());
        System.out.println(getEndDate());

    }
}
