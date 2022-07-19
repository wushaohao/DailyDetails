package designModels.Singleton.nolock;

/**
 * @author:wuhao
 * @description:饿汉式单例
 * @date:2019/12/19
 */
public class Singleton_nolock {

    private static Singleton_nolock instance = new Singleton_nolock();

    private Singleton_nolock() {
    }

    public static Singleton_nolock getInstance() {
        return instance;
    }
}
