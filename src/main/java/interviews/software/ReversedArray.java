package interviews.software;

/**
 * @author:wuhao
 * @description:逆序数组
 * @date:2022/7/22
 */
public class ReversedArray {
    /**
     * 找出数组中逆序对的个数
     * [3,1,2,5,4] => 3
     * 3,1 3,2 5,4
     *
     * @param arrays
     * @return
     */
    private static int Computer(int[] arrays) {
        if (arrays.length <= 0) {
            return 0;
        }
        int counts = 0;
        // 前后遍历 直到当前的数值相等break
        for (int i = 0; i < arrays.length; i++) {
            for (int j = arrays.length - 1; j > 0; j--) {
                if (i == j) {
                    break;
                }
                if (arrays[i] > arrays[j]) {
                    counts++;
                }
            }
        }
        return counts;
    }

    public static void main(String[] args) {
        int[] arrays = new int[]{3, 1, 2, 5, 4};
        System.out.println("results is:" + Computer(arrays));
        // 2147483647
//        System.out.println("Integer Max result is:"+Integer.MAX_VALUE);
    }

}

