package arithmetic;

/**
 * @author:wuhao
 * @description:快速排序算法
 * @date:18/9/6
 */
public class FastSort {

    public static void sort(int[] a, int low, int high) {
        int start = low;
        int end = high;
        int key = a[low];

        while (end > start) {
            //从后往前比较
            while (end > start && a[end] >= key) {
                //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                end--;
            }

            if (a[end] <= key) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }

            //从前往后比较
            while (end > start && a[start] <= key) {
                //如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                start++;
            }
            if (a[start] >= key) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
        }

        //递归
        if (start > low) {
            //左边序列。第一个索引位置到关键值索引-1
            sort(a, low, start - 1);
        }

        if (end < high) {
            //右边序列。从关键值索引+1到最后一个}
            sort(a, end + 1, high);
        }

    }

    public static void main(String[] args) {
        System.out.println("－－－－－－－－");
        int[] a = {12, 20, 5, 16, 15, 1, 30, 45, 23, 9};
        int start = 0;
        int end = a.length - 1;
        sort(a, start, end);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}


