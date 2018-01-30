package designModels.Proxy.DynProxy;

/**
 * Created by wuhao on 17/5/24.
 */
public class test {

    public static void main(String[] args) {
        // 目标对象
        IUserDao target=new UserDao();
        // 原始的类类型 class designModels.Proxy.DynProxy.UserDao
        System.out.println(target.getClass());

        //给目标对象创建代理对象
        IUserDao proxy= (IUserDao) new ProxyFactory(target).getProxyInstance();
        // 内存中创建代理对象  class com.sun.proxy.$Proxy0
        System.out.println(proxy.getClass());
        // 执行方法
        proxy.save();
    }
}
