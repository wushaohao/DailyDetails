package reference.weakreference;


import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author:wuhao
 * @description:弱引用测试
 * @date:18/8/7
 */
public class WeakReferenceTest {
    public static void main(String[] args) {
        Map<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(1);
        map.put(key, "test");
        // key不再有强引用
        key = null;
        System.gc();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map.size());

    }
}
