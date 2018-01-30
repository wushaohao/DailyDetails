package Reflect;

import Reflect.bean.user;

import java.lang.reflect.Field;

/**
 * Created by wuhao on 17/3/13.
 */
public class test1 {

    public static void main(String[] args) {
        Class clazz=user.class;

        Field[] field=clazz.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            System.out.println("获取的是:"+field[i].getGenericType());
        }
    }
}
