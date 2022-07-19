package StringTest;

/**
 * @author:wuhao
 * @description:
 * @date:18/9/4
 */
public class Test {

    /**
     * 用String保存的大数字
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String add(String str1, String str2) {
        String ret = "";
        int aLength = str1.length();
        int bLength = str2.length();
        int maxLen = aLength > bLength ? aLength : bLength;
        int minLen = aLength < bLength ? aLength : bLength;

        StringBuilder stringBuilder = new StringBuilder();
        /**
         *  相加--要保证两个数位数相同，位数少的前面补0
         */
        for (int i = 0; i < maxLen - minLen; i++) {
            stringBuilder.append(0);
        }

        if (minLen == aLength) {
            stringBuilder.append(str1);
        } else {
            stringBuilder.append(str2);
        }

        // 补位后最小字符串
        String minStr = stringBuilder.toString();

        stringBuilder = new StringBuilder();
        int tempA, tempB, result;
        /**
         * 进位
         */
        int sc = 0;
        for (int i = maxLen - 1; i >= 0; i--) {
            tempA = Integer.valueOf((str1.length() > str2.length() ? str1 : minStr).charAt(i));
            tempB = Integer.valueOf((str2.length() > str1.length() ? str2 : minStr).charAt(i));
            result = tempA + tempB + sc;
            sc = result / 10;
            result = result % 10;
            stringBuilder.append(result);
        }
        //如果加到最高为仍有进位，那么也要加上去
        if (sc == 1) {
            stringBuilder.append(1);
        }
        //因为是从个位依次相加的，所以结果要倒过来
        ret = stringBuilder.reverse().toString();

        return ret;
    }

    public static void main(String[] args) {
        String str1 = "9223372036854775807";
        String str2 = "372036854775807";

        System.out.println("结果是:" + add(str1, str2));

        Test test = new Test();
        Test test1 = new Test();
        System.out.println("hashCode():" + test.hashCode());
        System.out.println("hashCode():" + test1.hashCode());
        System.out.println("equals():" + test.equals(test1));
    }

}
