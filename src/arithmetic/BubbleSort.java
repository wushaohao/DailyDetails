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
        //
        int sortBoard = array.length - 1;


        for (int i = 0; i < array.length; i++) {
            // 是否继续要排序
            boolean isSort = true;

            for (int j = 0; j < sortBoard; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
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
        int[] array = new int[]{3, 4, 2, 1, 5, 6, 7, 8};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}

