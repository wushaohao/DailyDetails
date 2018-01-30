package designModels.Proxy.StaticProxy;

/**
 * Created by wuhao on 17/5/24.
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("save success...");
    }
}
