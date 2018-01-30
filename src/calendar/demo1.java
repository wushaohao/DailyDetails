package calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author wuhao
 * @date 17/7/15
 */
public class demo1 {

    private static String getFirstDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //设置日历中月份的第1天
        cal.set(Calendar.DAY_OF_MONTH, 1);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime());

        return firstDayOfMonth ;
    }

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //获取前月的第一天
        Calendar   cal_1=Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String firstDay = format.format(cal_1.getTime());
        System.out.println("-----1------firstDay:"+firstDay);
        //获取前月的最后一天
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
        String lastDay = format.format(cale.getTime());
        System.out.println("-----2------lastDay:"+lastDay);

        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        System.out.println("===============first:"+first);

        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        System.out.println("===============last:"+last);


        Calendar ca1 = Calendar.getInstance();
        ca1.setTime(new Date());                            //  someDate 为你要获取的那个月的时间
        ca1.set(Calendar.DAY_OF_MONTH,1);
        //第一天
        Date firstDate = ca1.getTime();
        System.out.println("firstDate:"+firstDate);
        ca1.add(Calendar.MONTH,1);
        ca1.add(Calendar.DAY_OF_MONTH,-1);
        //最后一天
        Date lastDate = ca1.getTime();
        System.out.println(lastDate);
    }
}
