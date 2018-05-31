package designModels.Singleton;

/**
 * @author:wuhao
 * @description:内部类懒汉式反射无法破坏单例
 * @date:18/5/31
 */
public class SingleModel6 {

    private static boolean initialized = false;

    private SingleModel6() {
        synchronized (SingleModel6.class) {
            if (initialized == false) {
                initialized = !initialized;
            } else {
                throw new RuntimeException("单例已被破坏");
            }
        }
    }

    static class SingleObject{
        private static SingleModel6 INSTANCE = new SingleModel6();
    }

    public static SingleModel6 getInstance() {
        return SingleObject.INSTANCE;
    }
}
