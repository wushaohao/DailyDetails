package interviews.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wuhao
 */
public class Sorts {

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<int[]> result = computer(nums);
        result.forEach(item -> {
            System.out.println(item);
        });
    }

    private static List<int[]> computer(int[] arrays) {
        List<int[]> lists = new ArrayList<>();
        // 排序先
        Arrays.sort(arrays);
        // 固定中间值i ,jk左右指针移动
        int length = arrays.length;
        int i = length / 2;
        for (int j = 0; j < i; j++) {
            for (int k = i; k < length; k++) {
                if (arrays[i] + arrays[j] + arrays[k] == 0) {
                    int[] newArr = new int[]{arrays[i], arrays[j], arrays[k]};
                    System.out.println("new array is:" + arrays[i] + " " + arrays[j] + " " + arrays[k]);
                    lists.add(newArr);
                }
            }
        }
        return lists;
    }
}
