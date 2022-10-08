package interviews;

import java.util.*;

/**
 * @author:wuhao
 * @description:string字符串的数字排序
 * @date:2022/10/8
 */
public class RandomStr {
    public static void main(String[] args) {
        String str = "gold3 white1 black2";
        System.out.println(Computer(str));
    }

    public static String Computer(String str) {
        if (str == null || str == "") {
            return null;
        }
        String[] arrays = str.split(" ");

        Map<Integer, String> maps = new HashMap<>();
        List<Integer> lists = new ArrayList<>();
        List<String> results = new ArrayList<>();

        for (int i = 0; i < arrays.length; i++) {
            int length = arrays[i].length();
            String temp = arrays[i].substring(length - 1, length);
            String value = arrays[i].substring(0, length - 1);
            int key = Integer.parseInt(temp);
            maps.put(key, value);
            lists.add(key);
        }

        Collections.sort(lists);
        for (Integer num : lists) {
            results.add(maps.get(num));
        }

        return results.toString();
    }
}
