package StringTest;

import java.util.regex.Pattern;

public class BigNumCal {
    private final static int threshold = 18;
    private final static Pattern pattern = Pattern.compile("\\D");

    public static void main(String[] args) {
        String s1 = "a1234";
        String s2 = "888888888888888888888888888888888888888888888888888888";
        add(s1, s2);
    }

    /**
     * 字符串加法运算
     */
    private static String summation(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int maxlen;
        String maxStr = null;
        String minStr = null;
        if (len1 > len2) {
            maxlen = len1;
            maxStr = s1;
            minStr = s2;
        } else {
            maxlen = len2;
            maxStr = s2;
            minStr = s1;
        }
        String temp = "";
        // 进位
        int carry = 0;
        String[] maxs = null;
        String[] mins = null;
        if (maxlen > threshold) {
            maxs = group(maxStr, maxlen);
            mins = group(minStr, maxlen);
        } else {
            maxs = new String[]{s1, ""};
            mins = new String[]{s2, ""};
        }

        //长度长的字符串分组后的长度，以这个长度为标准来进行循环计算
        //这里为了避免两个数组大小不等而引发的数组越界异常，单独写了一个方法，获取数组中的字符串getString（）方法
        int mins_len = mins.length;
        String max_str = null;
        String min_str = "";
        for (int i = maxs.length - 1; i >= 0; i--) {
            max_str = getString(maxs, i);

            if (mins_len >= 0) {
                min_str = getString(mins, mins_len - 1);
            } else {
                min_str = "";
            }

            String temp_str = parseString(max_str, min_str, carry);

            if (mins_len >= 0) {
                mins_len--;
            }
            //如果大于阈值，则进位置为1，截取阈值范围内的字符串(从第一位截取，第0位是进位)并保留，不再做运算
            if (temp_str.length() > threshold) {
                temp = temp_str.substring(1, temp_str.length()) + temp;
                carry = 1;
            } else {//如果小于阈值，但是结果的长度大于最大字符串数组某下标的长度，也进1，比如8+8=16，结果是16,2位数，大于1位数，所以也需要进1
                if (temp_str.length() > maxs[i].length()) {
                    temp = temp_str.substring(1, temp_str.length()) + temp;
                    carry = 1;
                } else {
                    temp = temp_str + temp;
                    carry = 0;
                }
            }
        }

        if (carry != 0) {
            //如果不为0则加上，否则就别加了，因为进位为0加上的话结果是"08787XXXX",开头是0，不好看
            temp = carry + temp;
        }
        return temp;
    }

    // 根据给定的两个String类型的数值，相加后，得到字符串结果，如果结果的长度大于19,说明已经超过了long的最大表数
    // 会自动判断传进来的字符串是否超过了long的最大表数范围，并判断2个字符串转换为long类型后的和是否超过long的表数范围，如果没超过，则进行long运算，否则进行字符串运算
    public static String add(String s1, String s2) {

        if (pattern.matcher(s1).find() || pattern.matcher(s2).find()) {
            throw new IllegalArgumentException("Illegal type of String [" + s1 + "],must be number of String");
        }

        long l1;
        long l2;

        try {
            l1 = Long.parseLong(s1);
            l2 = Long.parseLong(s2);

            if (l1 + l2 < 0) {
                return summation(s1, s2);
            } else {
                return String.valueOf(l1 + l2);
            }
        } catch (NumberFormatException e) {//如果捕获到格式化异常则说明溢出了，按字符串处理
            return summation(s1, s2);
        }
    }

    // 根据给定的两个long类型的数值，相加后，得到字符串结果，如果结果的长度大于19,说明已经超过了long的最大表数
    // 自动判断传入的long值，相加的和是否小于0，如果小于则超过了long的最大表数范围，用字符串处理E172629180
    public static String add(long l1, long l2) {
        if (l1 + l2 < 0) {
            return summation(String.valueOf(l1), String.valueOf(l2));
        } else {
            return String.valueOf(l1 + l2);
        }
    }

    // 根据给定的字符串，转换为long并求和，再转换为String
    private static String parseString(String s1, String s2, int i) {
        long l1 = 0;
        long l2 = 0;
        if (s1 != null && !s1.equals("")) {
            l1 = Long.parseLong(s1);
        }
        if (s2 != null && !s2.equals("")) {
            l2 = Long.parseLong(s2);
        }

        return (l1 + l2 + i > 0) ? (String.valueOf(l1 + l2 + i)) : "";
    }

    /**
     * 根据给定的字符串，长度len，截取字符串，如果s的长度大于阈值，则按len位一截取，否则不截取，返回字符串本身
     */
    private static String[] group(String s, int len) {
        String[] ss = null;
        if (s.length() > threshold) {
            ss = new String[(len / threshold) + 1];
            for (int i = 0; i <= len / threshold; i++) {
                if (i == len / threshold) {
                    ss[i] = s.substring(i * threshold, len);
                } else {
                    ss[i] = s.substring(i * threshold, i * threshold
                            + threshold);
                }
            }
        } else {
            ss = new String[]{s};
        }
        return ss;
    }

    /**
     * 根据给定的String数组，下标，判断如果没有越界，则返回下标所在的值，如果越界返回空字符串“”
     */
    private static String getString(String[] ss, int index) {
        if (index < 0) {
            return "";
        }
        if (index > ss.length - 1) {
            return "";
        } else {
            return ss[index];
        }
    }
}

