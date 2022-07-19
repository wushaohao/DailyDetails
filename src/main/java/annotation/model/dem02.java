package annotation.model;

import annotation.interfaces.people;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by wuhao on 17/3/12.
 */
@Description2("I am class annotation")
public class dem02 implements people {
    @Override
    public String getName() {
        return null;
    }

    @Override
    @Description2("I am methd annotation")
    public int getAge() {
        return 0;
    }

    @Override
    public String address() {
        return null;
    }

    public static void main(String[] args) {
        Class clazz = dem02.class;
        // 判断类上的注解
        boolean isExist = clazz.isAnnotationPresent(Description2.class);
        if (isExist) {
            // 解析拿到的类上的注解
            Description2 d = (Description2) clazz.getAnnotation(Description2.class);
            System.out.println(clazz.getName() + " 类上的注解内容是:" + d.value());
        }

        // 获取所有的方法
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            boolean misExist = methods[i].isAnnotationPresent(Description2.class);
            if (misExist) {
                Description2 dd = methods[i].getAnnotation(Description2.class);
                System.out.println(methods[i].getName() + " 方法上的注解内容是:" + dd.value());
            }
        }

        //
        for (Method m : methods) {
            Annotation[] a = m.getAnnotations();
            for (Annotation aa : a) {
                if (aa instanceof Description2) {
                    Description2 dd = (Description2) aa;
                    System.out.println(m.getName() + "的注解内容是:" + dd.value());
                }
            }
        }
    }
}
