package google.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;

/**
 * @author:wuhao
 * @description:缓存设计Demo--CacheLoader
 * @date:18/11/1
 */
public class CacheDemo {
    public static void main(String[] args) {
        LoadingCache<String, String> cacheBuilder = CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                String strProValue = "hello " + s + "!";
                return strProValue;
            }
        });

        System.out.println(cacheBuilder.apply("beginCode"));

        try {
            System.out.println(cacheBuilder.get("beginCode"));
            System.out.println(cacheBuilder.get("wen"));
            System.out.println(cacheBuilder.apply("wen"));
            System.out.println(cacheBuilder.apply("da"));
            cacheBuilder.put("begin", "code");
            System.out.println(cacheBuilder.get("begin"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
