package designModels.Singleton.nolock;

/**
 * @author:wuhao
 * @description:静态内部类:使用了lazy-loading.Singleton类被装载了，但是instance并没有立即初始化。 因为SingletonHolder类没有被主动使用，只有显示通过调用getInstance方法时，才会显示装载SingletonHolder类，从而实例化instance
 * @date:2019/12/19
 */
public class SingletonNolock {

    private static class Singleton {
        private static final SingletonNolock INSTANCE = new SingletonNolock();
    }

    private SingletonNolock() {
    }

    public static final SingletonNolock getInstance() {
        return Singleton.INSTANCE;
    }
}

