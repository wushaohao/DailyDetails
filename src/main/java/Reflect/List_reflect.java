package Reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhao on 17/1/10.
 * 通过返回结果，我们可以看到在编译之后集合的泛型是去泛型化的，java中集合类型的泛型，是防止错误输入的，只在编译阶段有效，
 * 绕过编译就无效了，所以我们通过方法的反射来操作，可以绕过编译
 */
public class List_reflect {

    public static void main(String[] args) {
        List<String> lists1=new ArrayList<String>();
        List lists2=new ArrayList();

        lists1.add("wuhao");

        Class c1=lists1.getClass();
        Class c2=lists2.getClass();

        try {
            Method m=c1.getMethod("add",Object.class);
            m.invoke(lists1,20);
            System.out.println(lists1.size());
            System.out.println(lists1);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}
