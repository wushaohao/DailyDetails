package designModels.Singleton;

/**
 * @author:wuhao
 * @description:饿汉式(初始化类的时候就初始化改单例对象)
 * @date:18/5/30
 */
public class SingleModel1 {
    /**
     * 优点就是线程安全啦，缺点很明显，类加载的时候就实例化对象了，浪费空间。于是乎，就提出了懒汉式的单例模式
     */
    private static SingleModel1 INSTANCE = new SingleModel1();

    private SingleModel1() {

    }

    public static SingleModel1 getINSTANCE() {
        return INSTANCE;
    }
}
