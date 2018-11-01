package google.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @author:wuhao
 * @description:guava缓存方式二--CallBack
 * @date:18/11/1
 */
public class CacheDemo2 {
    public static void main(String[] args) throws ExecutionException {
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(1000).build();
        String resultVal = cache.get("code", new Callable<String>() {
            @Override
            public String call() throws Exception {
                String strProValue = "begin " + "code" + "!";
                return strProValue;
            }
        });

        System.out.println("value : " + resultVal);
    }
}
