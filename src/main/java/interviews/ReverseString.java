package interviews;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author:wuhao
 * @description:字符串倒排序
 * @date:2020/3/16
 */
public class ReverseString {
    /**
     * 定义成一个StringBuffer类，用StringBuffer类中的reverse()方法直接倒序字符串
     *
     * @param str
     * @return
     */
    private String fun1(String str) {
        String results = null;
        if (StringUtils.isBlank(str)) {
            return null;
        } else {
            StringBuffer sb = new StringBuffer(str);
            results = sb.reverse().toString();
            System.out.println(results);
        }
        return results;
    }

    /**
     * 利用String类的toCharArray()，再倒序输出数组的方法
     *
     * @param str
     */
    private void fun2(String str) {
        if (StringUtils.isBlank(str)) {
            return;
        } else {
            char[] chars = str.toCharArray();
            for (int i = chars.length - 1; i >= 0; i--) {
                System.out.print(chars[i]);
            }
            System.out.println("\t");
        }
    }

    /**
     * 利用String类提供的subString()方法，利用递归的办法输出倒序字符串。
     *
     * @param str
     */
    private void fun3(String str) {
        if (str.length() == 1) {
            System.out.println(str);
        } else {
            String subStr = str.substring(0, str.length() - 1);
            String subStr2 = str.substring(str.length() - 1);
            System.out.print(subStr2);
            fun3(subStr);
        }
    }

    private void fun4(String str) {
        if (str.length() >= 1) {
            for (int i = str.length(); i > 0; i--) {
                System.out.print(str.substring(i - 1, i));
            }
            System.out.println("\t");
        } else {
            return;
        }
    }

    /**
     * 字符串单词反转
     *
     * @param str
     */
    private void reverseWord(String str) {
        char[] splits = ", ".toCharArray();
        Set<Character> splitCharSet = new HashSet<Character>();
        for (char split : splits) {
            splitCharSet.add(split);
        }
        StringBuilder reverseStr = new StringBuilder();
        StringBuilder rsIndex = new StringBuilder();
        Character indexChar;
        for (int i = str.length() - 1; i > -1; i--) {
            indexChar = Character.valueOf(str.charAt(i));
            if (!splitCharSet.contains(indexChar)) {
                rsIndex.append(indexChar);
            } else {
                reverseStr.append(rsIndex.reverse()).append(indexChar);
                rsIndex.delete(0, rsIndex.length());
            }
        }
        reverseStr.append(rsIndex.reverse());
        System.out.println(reverseStr);
    }

    /**
     * \b 是匹配空格 \\b 是边界匹配 匹配 word与非word毗邻，非word与word毗邻 的格式 ，然后返回匹配的第二个字符
     * ，每一个字符串最前面和最后面都有一个看不见的非word,word 包括a-z，A-Z ，0-9 eg: "\\b" 去匹配 "w2w w$ &#w2",匹配的下标是：0,3,4,5,9,11
     * "\\b"去匹配 “#ab de#“,匹配的下标是：1,3,4,6 \\B 与\\b相反 匹配word与word毗邻，非word与非word毗邻的格式，然后返回第二个字符 \b 是语法
     * 匹配一个单词边界，也就是指单词和空格间的位置 \\b 是转译 匹配 “\b"
     *
     * @param str
     */
    private void reverseWord2(String str) {
        String[] arr = str.split("\\b");
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {
        String testStr = "I am a student";
        ReverseString reverseString = new ReverseString();
        System.out.println("reverse*******");
        reverseString.fun1(testStr);
        System.out.println("char[]********");
        reverseString.fun2(testStr);
        System.out.println("substring()********");
        reverseString.fun3(testStr);
        System.out.println("substring()2********");
        reverseString.fun4(testStr);
        System.out.println("字符串反转1:");
        reverseString.reverseWord(testStr);
        System.out.println("字符串反转2:");
        reverseString.reverseWord(testStr);
    }
}
