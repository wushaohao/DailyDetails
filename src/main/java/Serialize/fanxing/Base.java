package Serialize.fanxing;


import com.alibaba.fastjson.JSON;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author:wuhao
 * @description:父类
 * @date:18/12/14
 */
public abstract class Base<T extends Comparable<T>> {
    T data;

    public Base(String json) {
        this.data = JSON.parseObject(json, deSerializable());
    }

    private Class<T> deSerializable() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            System.out.println(parameterizedType.getActualTypeArguments()[0]);
            return (Class<T>) parameterizedType.getActualTypeArguments()[0];
        }
        throw new RuntimeException();
    }
}
