package interviews;

import java.util.Scanner;

/**
 * 字母转换成数字
 */
public class TestConvert {

    /**
     * 将字母转换成数字_1
     */
    public static String t1(String input) {
        String reg = "[a-zA-Z]";
        StringBuffer strBuf = new StringBuffer();
        input = input.toLowerCase();
        if (null != input && !"".equals(input)) {
            for (char c : input.toCharArray()) {
                if (String.valueOf(c).matches(reg)) {
                    strBuf.append(c - 96);
                } else {
                    strBuf.append(c);
                }
            }
            return strBuf.toString();
        } else {
            return input;
        }
    }

    /**
     * 将字母转换成数字
     */
    public static void letterToNum(String input) {
        for (byte b : input.getBytes()) {
            System.out.print(b - 96);
        }
    }

    /**
     * 将数字转换成字母
     */
    public static void numToLetter(String input) {
        for (byte b : input.getBytes()) {
            System.out.print((char) (b + 48));
        }
    }

    private static void calculate(String str) {
        String[] arr = str.split("\\s+");
        System.out.println("arr.length:" + arr.length);
        int result = 0;
        int[] arr2 = new int[arr.length];

        if (arr.length == 0) {
            return;
        } else {
            for (int i = 0; i < arr.length; i++) {
                arr2[i] = judge(arr[i]);
            }
        }
        System.out.println(arr2.length);
        result = (arr2[2] - arr2[0]) + (arr2[4] - arr2[3]);
        System.out.println(result);
    }

    private static int judge(String str) {
        int num = 0;
        if (str.contains("Y")) {
            int index = str.indexOf("Y");
            System.out.println(index);
            num = Integer.valueOf(str.substring(0, index));
            return num;
        } else {
            int index = str.indexOf("S");
            num = Integer.valueOf(str.substring(0, index));
            return num * 7;
        }
    }

    public static void main(String[] args) {
//    Scanner in = new Scanner(System.in);
//    calculate(in.next());
        //
        //    int num = in.nextInt();
        //    String str1 = String.valueOf(num);
        //    System.out.println(str1);
        //    numToLetter(str1);
        //    String i1 = "abcdef";
        //    String i2 = "123456";
        //    letterToNum(i1);
        //    System.out.println();
        //    numToLetter(in.next());

        //    System.out.println(judge("5S"));
        calculate("2Y 3S 4S 6Y 8S");
    }
}
