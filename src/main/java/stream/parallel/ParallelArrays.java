package stream.parallel;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author:wuhao
 * @description:并行数组
 * @date:18/12/3
 */
public class ParallelArrays {
    public static void main(String[] args) {
        /**
         * 下面这些代码使用parallelSetAll()方法生成20000个随机数，然后使用parallelSort()方法进行排序。这个程序会输出乱序数组和排序数组的前10个元素
         */
        long[] arrayOfLong = new long[20000];

        // 生成的随机数去重
        Arrays.parallelSetAll(arrayOfLong, index -> ThreadLocalRandom.current().nextInt(1000000));
        Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.print(i + ""));
        System.out.println();

        // 生成的随机数进行排序
        Arrays.parallelSort(arrayOfLong);
        Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.print(i + ""));
        System.out.println();
    }
}
