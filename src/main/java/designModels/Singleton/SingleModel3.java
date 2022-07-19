package designModels.Singleton;

/**
 * @author:wuhao
 * @description:饿汉式单例(线程安全)
 * @date:18/5/30
 */
public class SingleModel3 {
    private static SingleModel3 INSTANCE;

    private SingleModel3() {

    }

    public synchronized static SingleModel3 getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new SingleModel3();
        }
        return INSTANCE;
    }
}
