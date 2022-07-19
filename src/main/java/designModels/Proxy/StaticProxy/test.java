package designModels.Proxy.StaticProxy;

/**
 * Created by wuhao on 17/5/24.
 */
public class test {
    public static void main(String[] args) {
        // 目标对象
        UserDao dao = new UserDao();

        // 代理对象 包目标传给代理对象
        ProxyFactory proxy = new ProxyFactory(dao);

        // 执行代理对象的方法
        proxy.doProxy();
    }
}
