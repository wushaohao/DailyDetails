package google.guava.gson;

import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:wuhao
 * @description:客户端测试类
 * @date:2019-08-14
 */
public class MainTest {
    public static void main(String[] args) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(22);
        userInfo.setName("hulk");
        userInfo.setSex("F");
        // @Expose 想显示谁就显示谁
        //    String result =
        // gsonBuilder.excludeFieldsWithoutExposeAnnotation().create().toJson(userInfo);
        // @SerializedName 想怎么显示就怎么显示
        //    String result2 = gsonBuilder.create().toJson(userInfo);
        //    System.out.println("result:" + result);
        //    System.out.println("result2:" + result2);
        //@Since标注的在 3.2 版本或之后才会输出，@Until标注的只在 3.2 版本前才有
        gsonBuilder.setVersion(3.2);
        String result = gsonBuilder.create().toJson(userInfo);
        System.out.println("result" + result);

        Map<String, String> maps = new HashMap<String, String>();
        maps.put("name", "hulk");
        maps.put("age", "22");
        maps.put("sex", null);
        // Gson序列化会直接过滤掉为null的
        String jsonStr = new GsonBuilder().create().toJson(maps);
        // serializeNulls() 序列化会将为空的也序列化出来
        String jsonStrOfNull = new GsonBuilder().serializeNulls().create().toJson(maps);
        System.out.println("jsonStr:" + jsonStr + "\t jsonStrOfNull" + jsonStrOfNull);
    }
}
