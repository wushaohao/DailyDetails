package fanxing;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author:wuhao
 * @description:测试类
 * @date:2019/12/17
 */
public class BaseBeanClient {

    public static void main(String[] args) {
        BaseBean<String> stringBaseBean = new BaseBean<>();

        stringBaseBean.setValue("China");

        /**
         * 1.通过反射获取到的属性是Object类型的，在方法中返回的是string类型，因此咱们可以思考在getValue方法里面实际是做了个强转的动作，
         * 将object类型的value强转成string类型。
         *
         * 2.泛型只是为了约束我们规范代码，而对于编译完之后的class交给虚拟机后，对于虚拟机它是没有泛型的说法的，所有的泛型在它看来都是object类型，
         * 因此泛型擦除是对于虚拟机而言的.
         *
         * 3.说了泛型其实对于jvm来说都是Object类型的，那咱们直接将类型定义成Object不就是的了，这种做法是可以，但是在拿到Object类型值之后，
         * 自己还得强转，因此泛型减少了代码的强转工作，而将这些工作交给了虚拟机
         */
        try {
            //获取属性上的范型类型
            Field value = stringBaseBean.getClass().getDeclaredField("value");
            Class type = value.getType();
            String name = type.getName();
            System.out.println("type:" + name);

            //获取方法上的范型类型
            Method getValue = stringBaseBean.getClass().getDeclaredMethod("getValue");
            Object invoke = getValue.invoke(stringBaseBean);
            String methodName = invoke.getClass().getName();
            System.out.println("methodName:" + methodName);
        } catch (NoSuchFieldException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
