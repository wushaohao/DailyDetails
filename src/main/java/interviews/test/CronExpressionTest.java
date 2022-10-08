package interviews.test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author:wuhao
 * @description:表达式判断时间是否能执行
 * @date:2022/8/30
 */
public class CronExpressionTest {

    private static String ALL = "*";
    private static String PART = "-";
    private static String PART_ = ",";
    private static String FEN = "/";

    // 0 0 0 * * * , 2019/6/24 00:00:00
    public static boolean canRun(String exp, Date date) {
        if (exp == null || date == null) {
            return false;
        }

        String[] strs = exp.split(" ");
        // 秒
        boolean seconds = judge(strs, date.getSeconds(), 0);
        // 分
        boolean minutes = judge(strs, date.getMinutes(), 1);
        // 时
        boolean hours = judge(strs, date.getHours(), 2);
        // 天
        boolean days = judge(strs, date.getDay(), 3);
        // 月
        boolean months = judge(strs, date.getMonth(), 4);

//        return seconds || (minutes || (hours || (days || months)));
        return seconds || minutes || hours || days || months;
    }

    public static boolean judge(String[] exps, int params, int i) {
        if (exps[i].equals(ALL)) {
            System.out.println("ALL:* pass");
            return true;
        }

        if (exps[i].contains(PART)) {
            String[] temp = exps[i].split(PART);
            int start = Integer.parseInt(temp[0]);
            int end = Integer.parseInt(temp[temp.length - 1]);
            if (params >= start && params <= end) {
                System.out.println("PART:- pass");
                return true;
            }
        }

        if (exps[i].contains(PART_)) {
            String[] temp = exps[i].split(PART_);
            for (int j = 0; j < temp.length; j++) {
                if (Integer.parseInt(temp[i]) == params) {
                    System.out.println("PART_:, pass");
                    return true;
                }
            }
        }

        if (exps[i].contains(FEN)) {
            String[] temp = exps[i].split(FEN);
            int values = Integer.parseInt(temp[1]);
            if (params % values == 0) {
                System.out.println("FEN:/ pass");
                return true;
            }
        }
        System.out.println("no params check  no pass");
        return false;
    }

    public static void main(String[] args) throws ParseException {
        String exp = "0 0 0 * * *";
        Date date = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").parse("2019-06-24 00:00:00");
        boolean result = canRun(exp, date);
        System.out.println("Date can run:" + result);
    }
}
