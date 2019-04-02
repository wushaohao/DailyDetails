package interviews;

/**
 * @author:wuhao
 * @description:两个大数相加
 * @date:2019/4/2
 */
public class BigNumberSum {


    /**
     * 时间复杂度度:
     * 如果给定大整数的最长位数是n,那么创建数据、按位计算、结果逆序的时间复杂度各自都是O(n),整体复杂度是O(n)
     * @param numberA
     * @param numberB
     * @return
     */
    public static String sum(String numberA, String numberB) {
        //1.把两个大整数用数组逆序存储
        char[] charsA = new StringBuffer(numberA).reverse().toString().toCharArray();
        char[] charsB = new StringBuffer(numberB).reverse().toString().toCharArray();

        //2.构建results数组 数组长度等于较大整数位数+1
        int maxLength = charsA.length > charsB.length ? charsA.length : charsB.length;
        int[] results = new int[maxLength + 1];


        // 遍历数组相加
        for (int i = 0; i < results.length; i++) {
            int temp = results[i];
            if (i < charsA.length) {
                temp += charsA[i] - '0';
            }
            if (i < charsB.length) {
                temp += charsB[i] - '0';
            }
            //判断是否进位
            if (temp > 10) {
                temp = temp - 10;
                results[i + 1] = 1;
            }
            results[i] = temp;
        }

        //4.把results数组再次逆序并转成String
        StringBuffer sb = new StringBuffer();
        boolean flag = true;
        for (int i = results.length - 1; i > 0; i--) {
            if (results[i] == 0 && flag) {
                continue;
            }
            flag = false;
            sb.append(results[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(sum("426709752318","95481253129"));
    }
}
