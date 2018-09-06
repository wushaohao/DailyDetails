package arithmetic;

import java.util.Arrays;

/**
 * @author:wuhao
 * @description:冒泡排序
 * @date:18/7/21
 */
public class BubbleSort {
    public static void sort(int[] array) {
        int temp = 0;
        int lastChangeIndex = 0;
        // 排序边界
        int sortBoard = array.length - 1;

        for (int i = 0; i < array.length; i++) {
            // 有序标记(数组已经是有序的) 每一轮开始的时候是true
            boolean isSort = true;

            for (int j = 0; j < sortBoard; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    //有元素交换 所以不是有序的 标记变为false
                    isSort = false;
                    lastChangeIndex = j;
                }
            }

            sortBoard = lastChangeIndex;
            if (isSort) {
                break;

            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 2, 1, 7, 6, 8, 5};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}

