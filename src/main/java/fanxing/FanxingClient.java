package fanxing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author:wuhao
 * @description:范型测试类
 * @date:2019/12/17
 */
public class FanxingClient {
    public static void main(String[] args) {
        /**
         * 在定义的时候将Common的泛型指向Common1的泛型，可以看到直接提示有问题，这里可以想，虽然Common1是继承自Common的，但是并不代表BaseBean之间是等量的，
         * 在开篇也讲过，如果泛型传入的是什么类型，那么在BaseBean中的getValue返回的类型就是什么，因此可以想两个不同的泛型类肯定是不等价的
         */

        BaseBean<Common> commonBase = new BaseBean<>();
        //      BaseBean<Common1> common1BaseBean = commonBase;
        // 通配符定义就没问题
        BaseBean<?> common1BaseBean = new BaseBean<>();
        // 通配符不能定义在类上面、接口或方法上，只能作用在方法的参数上
        try {
            // 通过反射猜测setValue的参数是Object类型的
            Method setValue = common1BaseBean.getClass().getDeclaredMethod("setValue", Object.class);
            setValue.invoke(common1BaseBean, "123");
            Object value = common1BaseBean.getValue();
            System.out.println("result:" + value);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
