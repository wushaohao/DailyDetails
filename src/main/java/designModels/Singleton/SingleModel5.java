package designModels.Singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author:wuhao
 * @description:懒汉式(静态内部类)
 * @date:18/5/31
 */
public class SingleModel5 {
    private SingleModel5() {

    }

    /**
     * 1.由于对象实例化是在内部类加载的时候构建的，因此该版是线程安全的(因为在方法中创建对象，
     * 才存在并发问题，静态内部类随着方法调用而被加载，只加载一次，不存在并发问题，所以是线程安全的)
     * 2.在getInstance()方法中没有使用synchronized关键字，因此没有造成多余的性能损耗。当LazySingleton2类加载的时候，
     * 其静态内部类SingletonHolder并没有被加载，因此instance对象并没有构建
     * 3.而我们在调用LazySingleton2.getInstance()方法时，内部类SingletonHolder被加载，此时单例对象才被构建。
     * 因此，这种写法节约空间，达到懒加载的目的
     */
    static class SingleObject {
        private static SingleModel5 INSTANCE = new SingleModel5();
    }

    public static SingleModel5 getInstance() {
        return SingleObject.INSTANCE;
    }

    /**
     * 反射会破坏此单例
     *
     * @param args
     */
    public static void main(String[] args) {
        // 创建第一个实例
        SingleModel5 instance1 = SingleModel5.getInstance();

        SingleModel5 instance2 = null;
        Class<SingleModel5> clazz = SingleModel5.class;
        try {
            Constructor<SingleModel5> cons = clazz.getDeclaredConstructor();
            cons.setAccessible(true);
            instance2 = cons.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("Instance1 hash" + instance1.hashCode());
        System.out.println("Instance2 hash" + instance2.hashCode());
    }
}
