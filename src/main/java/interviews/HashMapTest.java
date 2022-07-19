package interviews;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * @author:wuhao
 * @description:HashMap的测试类
 * @date:2021/12/5
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String, String> maps = new HashMap<>();

        maps.put(null, "1");
        maps.put(null, "2");

        System.out.println(maps.size());

        Hashtable<String, String> table = new Hashtable<>();
        table.put(null, "1");
        System.out.println(table.size());

    }
}
