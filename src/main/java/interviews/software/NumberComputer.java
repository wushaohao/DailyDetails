package interviews.software;

/**
 * @author:wuhao
 * @description:计算数值的精确
 * @date:2021/9/30
 */
public class NumberComputer {
    public static void main(String[] args) {
        String numStr = "3.3435";
        System.out.println(computer(numStr, 3));

        String numStr2 = "3.8765";
        System.out.println(computer(numStr2, 3));

        String numStr3 = "3.88501";
        System.out.println(computer(numStr3, 2));
    }

    /**
     * @param numStr 数值
     * @param fixed  精度
     */
    private static String computer(String numStr, int fixed) {
        /**
         *  1) 3.3435 => 3.344
         * 	2) 3.8765 => 3.876
         * 	3) 3.9287 => 3.929
         * 	4) 3.2732 => 3.273
         * 	5) 3.6875 => 3.688
         *  6) 3.3125 => 3.312
         *  7) 8.88501 => 8.89
         */
        Integer index = numStr.indexOf(".");
        /** 没有小数，直接返回原数*/
        if (index == -1) {
            return numStr;
        }
        /** 去除小数点后的尾部0,保留到保留位数*/
        numStr = removeEndZero(numStr, fixed);
        /** 取到保留小数位的下一位，5.5555取保留俩位，那就是小数点后第三位*/
        Integer indexFixed = index + fixed + 1;
        /** 如果小数位数不够补零后就直接返回，不做其他处理*/
        if (indexFixed >= numStr.length()) {
            return zeroFill(numStr, fixed);
        }
        /** 取保留位数的后一位做判断位，用来判断是否进位*/
        String judgeNumber = numStr.substring(indexFixed, indexFixed + 1);
        /** 如果判断位不为五，按四舍六入算，也就是正常的四舍五入就行了，numStr不是数字会报异常,"%."+ fixed + "f";fixed是几位就保留几位*/
        if (!"5".equals(judgeNumber)) {
            numStr = String.format("%." + fixed + "f", Double.parseDouble(numStr));
            return zeroFill(numStr, fixed);
        }
        /** 判断位为5，看小数位最后一位是不是0，不是0进位，是0就看前面一位是奇数还是偶数，奇数进偶数不进，由于末尾多余0已去除，所以只需要判断判断位是不是最后一位，是就看奇偶，不是直接进位*/
        if (indexFixed < (numStr.length() - 1)) {
            /** 五后有数且不为0进位，此处要考虑到是正数还是负数,startsWith("-")检测前缀以-开始,先要截取到保留的位数，在进行计算*/
            /** 要考虑到1440.5这种情况取0位小数会留下逗号，*/
            numStr = handleRadixPoint(numStr, indexFixed);
            numStr = plusMinusCarry(numStr, fixed);
            return zeroFill(numStr, fixed);
        }
        /** 看奇进偶不进，取当前判断位的上一位作为判断位*/
        judgeNumber = numStr.substring(indexFixed - 1, indexFixed);
        /** 如果取到的判断位是.表示需要重新再取点的左边一位，也就是个位*/
        if (".".equals(judgeNumber)) {
            judgeNumber = numStr.substring(indexFixed - 2, indexFixed - 1);
        }
        /** 偶不进，如果是1440.5这种情况取0位小数会留下逗号，需要加判断。判断位是'.'的，截取字符需要排除'.',小数位不够需补零*/
        numStr = handleRadixPoint(numStr, indexFixed);
        /** 奇进:判断位为奇数就进位*/
        if ("13579".contains(judgeNumber)) {
            numStr = plusMinusCarry(numStr, fixed);
        }

        return zeroFill(numStr, fixed);
    }

    /**
     * 逗号处理，截取小数位，比如1440.5这种情况取0位小数会留下逗号
     */
    public static String handleRadixPoint(String numStr, Integer indexFixed) {
        if (".".equals(numStr.substring(indexFixed - 1, indexFixed))) {
            numStr = numStr.substring(0, indexFixed - 1);
        } else {
            numStr = numStr.substring(0, indexFixed);
        }
        return numStr;
    }

    /**
     * 去除小数后的尾部多余零，保留到保留位数的结果,numStr.substring(0, numStr.length() - 1);从0开始，取numStr.length() - 1的下标位位，
     * substring的参数2时从0开始算但是0时截取不到字符的，1开始才可以截取字符的。
     */
    public static String removeEndZero(String numStr, Integer fixed) {
        if (fixed == numStr.substring(numStr.indexOf("."), (numStr.length() - 1)).length()) {
            return numStr;
        } else if ("0".equals(numStr.substring(numStr.length() - 1))) {
            numStr = numStr.substring(0, numStr.length() - 1);
            numStr = removeEndZero(numStr, fixed);
        } else if (".".equals(numStr.substring(numStr.length() - 1))) {
            numStr = numStr.substring(0, numStr.length() - 1);
        }
        return numStr;
    }

    /**
     * 正负数判断进位
     */
    public static String plusMinusCarry(String numStr, Integer fixed) {
        System.out.println("numStr:" + numStr);
        if (numStr.startsWith("-")) {
            numStr = String.format("%." + fixed + "f", Double.parseDouble(numStr) - Math.pow(10, -fixed));
        } else {
            numStr = String.format("%." + fixed + "f", Double.parseDouble(numStr) + Math.pow(10, -fixed));
        }

        return numStr;
    }

    /**
     * 补零，当小数位不够时需要补零
     */
    public static String zeroFill(String numStr, Integer fixed) {
        /** 根据传入的保留小数位数，将不够的小数位数补0,保留小数位-现有的小数位，如果大于0表示要补零，其他原样返回*/
        if (numStr.indexOf(".") == -1) {
            return numStr;
        }
        fixed = fixed - numStr.substring(numStr.indexOf("."), (numStr.length() - 1)).length();
        if (fixed > 0) {
            for (int i = 0; i < fixed; i++) {
                numStr += "0";
            }
        }
        return numStr;
    }
}
