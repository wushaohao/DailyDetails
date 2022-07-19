package interviews;

import java.util.Scanner;

/**
 * @author:wuhao
 * @description:面试算法题
 * @date:2020/4/14
 */
public class IslandOrSeaTest {
    private static final int MAX = 16;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = Integer.valueOf(scanner.next());
        int n = Integer.valueOf(scanner.next());
        System.out.println("m: " + m + "\t n:" + n);
        if (m > MAX || n > MAX) {
            return;
        }
        int[][] array = new int[m][n];
        System.out.println("数组的长度是:" + array.length);

        for (int i = 0; i < array.length; i++) {
            scanner.nextLine();

        }

        System.out.println(array[0][1]);

    }

    public static void test(int[][] array) {

    }
}
