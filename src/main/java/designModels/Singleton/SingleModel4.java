package designModels.Singleton;

/**
 * @author:wuhao
 * @description:懒汉式(双重检查机制)
 * @date:18/5/30
 */
public class SingleModel4 {
    private static SingleModel4 INSTANCE;

    private SingleModel4() {

    }

    public static SingleModel4 getINSTANCE() {
        if (null == INSTANCE) {
            synchronized (SingleModel4.class) {
                if (null == INSTANCE) {
                    INSTANCE = new SingleModel4();
                }
            }
        }
        return INSTANCE;
    }
}
