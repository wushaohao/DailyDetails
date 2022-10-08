package interviews.linkedlist;

/**
 * @author:wuhao
 * @description:数组元素相加
 * @date:2022/8/10
 */
public class SumLink {
    public static void main(String[] args) {
        int[] array1 = new int[]{7, 2, 4, 3};
        int[] array2 = new int[]{5, 6, 4};
        System.out.println(computer(array1, array2));
    }

    public static int computer(int[] array1, int[] array2) {
        String sum1 = "";
        String sum2 = "";
        for (int i = 0; i < array1.length; i++) {
            sum1 = sum1 + array1[i];
        }
        for (int j = 0; j < array2.length; j++) {
            sum2 = sum2 + array2[j];
        }
        int result = Integer.parseInt(sum1) + Integer.parseInt(sum2);
        return result;
    }


}
