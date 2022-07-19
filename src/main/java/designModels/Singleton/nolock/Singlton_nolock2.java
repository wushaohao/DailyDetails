package designModels.Singleton.nolock;


/**
 * @author:wuhao
 * @description:饿汉式变种2:使用static来定义静态成员变量或静态代码，借助Class的类加载机制实现线程安全单例
 * @date:2019/12/19
 */
public class Singlton_nolock2 {
    private static Singlton_nolock2 instance = null;

    static {
        instance = new Singlton_nolock2();
    }

    private Singlton_nolock2() {
    }

    public static Singlton_nolock2 getInstance() {
        return instance;
    }
}
