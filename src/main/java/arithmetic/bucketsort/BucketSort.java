package arithmetic.bucketsort;

import java.util.Random;

/**
 * @author:wuhao
 * @description:桶排序
 * @date:18/10/10
 */
public class BucketSort {
    // 初始化一个空数组
    private static int[] arrs = new int[10];

    /**
     * 排序
     *
     * @param num
     */
    public static void sort(int num) {
        arrs[num]++;
    }

    /**
     * 这个算法好比 将所有要排序的元素 存储在一个初始化大小和需要排序的元素最大数组内
     * 将排序的元素放置在该初始化数组对应的序列位置处并执行+1 有相同的元素就执行+1操作
     * 最后遍历数组 循环输出数组的序列值
     *
     * @param args
     */
    public static void main(String[] args) {
        // 循环几个数
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            int index = random.nextInt(10);
            System.out.println(index);
            sort(index);
        }

        for (int m = arrs.length - 1; m >= 0; m--) {
            if (arrs[m] > 0) {
                for (int j = 0; j < arrs[m]; j++) {
                    System.out.println("输出的结果是:" + m);
                }
            }
        }
    }
}
