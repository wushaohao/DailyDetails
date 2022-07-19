package Serialize.fanxing;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author:wuhao
 * @description:测试主类
 * @date:18/12/14
 */
public class Main {
    public static void main(String[] args) {
        /**
         * 将一个对象序列化成 Json 字符串，模拟外部输入。然后呢？创建一个子类对象，得到这个Son的Class.
         * 关键地方来了，调用 getGenericSuperclass 方法，
         * 这个方法的作用是：返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type
         */
        String json = JSON.toJSONString(new DataClass());
        Son s = new Son(json);
        Type type = s.getClass().getGenericSuperclass();

        if (type instanceof ParameterizedType) {
            System.out.println(type);
            // output: cn.think.in.java.clazz.loader.generics.Base<cn.think.in.java.clazz.loader.generics.DataClass>
            for (Type t : ((ParameterizedType) type).getActualTypeArguments()) {
                //output: class cn.think.in.java.clazz.loader.generics.DataClass
                System.out.println(t);
            }
        }
    }
}
