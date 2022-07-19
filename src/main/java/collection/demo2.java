package collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuhao
 * @date 17/6/19
 */
public class demo2 {
    public static void main(String[] args) {
        List<String> lists = new ArrayList<>();
        lists.add(null);
        System.out.println("lists size is " + lists.size() + " lists is empty:" + lists.isEmpty());


        List<String> lists2 = new ArrayList<>();
        lists.add("2222222");

        long a = 2222222;
        System.out.println(lists2.contains(a));
    }
}
