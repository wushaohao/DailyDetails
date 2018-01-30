package designModels.Proxy.Cglib;

/**
 *
 * @author wuhao
 * @date 17/5/24
 */
public class test {

    public static void main(String[] args) {
        // 目标对象
        UserDao userDao=new UserDao();
        // 代理对象
        UserDao proxy= (UserDao) new ProxyFactory(userDao).getProxyInstance();
        // 执行代理对象的方法
        proxy.save();
    }

}
