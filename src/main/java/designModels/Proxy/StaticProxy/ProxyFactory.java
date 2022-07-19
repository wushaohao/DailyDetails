package designModels.Proxy.StaticProxy;

/**
 * Created by wuhao on 17/5/24.
 */
public class ProxyFactory {
    private IUserDao target;

    public ProxyFactory(IUserDao target) {
        this.target = target;
    }

    public void doProxy() {
        System.out.println("Static proxy start..");
        target.save();
        System.out.println("Static proxy end..");
    }
}
