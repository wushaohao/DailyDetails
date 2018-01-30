package Reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by wuhao on 17/2/23.
 */
public class testFieldType {

    private String userName;

    private String passWord;

    private int age;

    private String address;

    public void getName(String userName){
        this.userName=userName;
        System.out.println(">>>>>");
    }

    public static void main(String[] args) throws Exception{
        Class clazz=testFieldType.class;

        Field[] fields=clazz.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getName()+"\t"+fields[i].getType().getName());
        }

        Method[] methods=clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Class<?>[] type=methods[i].getParameterTypes();

            for (int j = 0; j < type.length; j++) {
                System.out.println("参数类型是"+type[j].getName());
            }
        }

    }
}
