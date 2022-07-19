package designModels.Singleton;

/**
 * @author:wuhao
 * @description:懒汉式单例(线程不安全)
 * @date:18/5/30
 */
public class SingleModel2 {
    private static SingleModel2 INSTANCE = null;

    private SingleModel2() {

    }

    public static SingleModel2 getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new SingleModel2();
        }
        return INSTANCE;
    }
}
