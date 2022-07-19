package StringTest;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author:wuhao
 * @description:ConcurrentHashMap putIfAbsent方法主要是在向ConcurrentHashMap中添加键—值对的时候，它会先判断该键值对是否已经存在。
 * 如果不存在（新的entry），那么会向map中添加该键值对，并返回null。
 * 如果已经存在，那么不会覆盖已有的值，直接返回已经存在的值
 * @date:18/6/23
 */
public class ConcurrentHashMap1 {

    private static Object getValue(String key) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        Object obj = concurrentHashMap.get(key);
        if (obj != null) {
            System.out.println("直接获取到值:" + obj);
        }

        concurrentHashMap.size();

        obj = concurrentHashMap.putIfAbsent(key, "cai");
        if (obj == null) {
            System.out.println("塞值之后获取到:" + concurrentHashMap.get(key));
            return concurrentHashMap.get(key);
        }
        return obj;
    }

    public static void main(String[] args) {
        getValue("serviceCode");
    }
}
