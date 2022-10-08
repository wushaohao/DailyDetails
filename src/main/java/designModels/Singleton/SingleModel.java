package designModels.Singleton;

/**
 * @author:wuhao
 * @description:volatile单例模式
 * @date:2022/9/9
 */
public class SingleModel {
    private static volatile SingleModel instance = null;

    private SingleModel() {

    }

    public static SingleModel getInstance() {
        if (instance == null) {
            synchronized (SingleModel.class) {
                if (null == instance) {
                    instance = new SingleModel();
                }
            }
        }
        return instance;
    }
}
