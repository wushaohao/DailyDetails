package arithmetic.countsort;

import java.util.Arrays;

/**
 * @author:wuhao
 * @description:计数排序
 * @date:18/10/12
 */
public class CountSort {

    public static int[] getArray(int[] arr) {
        // 数组中的最大值
        int max = arr[0];
        // 数组中的最小值
        int min = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }

            if (arr[i] < min) {
                min = arr[i];
            }
        }

        int newLength = max - min + 1;
        // 创建统计数组并统计对应元素个数
        int[] countArray = new int[newLength];
        for (int i = 0; i < arr.length; i++) {
            countArray[arr[i] - min]++;
        }

        // 统计数组做变形，后面的元素等于前面的元素之和
        int sum = 0;
        for (int i = 0; i < countArray.length; i++) {
            sum += countArray[i];
            countArray[i] = sum;
        }

        // 倒叙排序
        int[] sortArray = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            sortArray[countArray[arr[i] - min] - 1] = arr[i];
            countArray[arr[i] - min]--;
        }

        return sortArray;
    }


    public static void main(String[] args) {
        int[] array = new int[]{95, 94, 91, 98, 99, 90, 99, 93, 91, 92};
        int[] sortArray = getArray(array);
        System.out.println(Arrays.toString(sortArray));

    }

}
