package designModels.Proxy.DynProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wuhao on 17/5/24.
 */
public class ProxyFactory {

    // 目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 给目标生成代理对象
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("Dyn proxy start...");
                        // 执行目标对象方法
                        Object returnValue = method.invoke(target, args);
                        System.out.println("Dyn proxy submit...");
                        return returnValue;
                    }
                }
        );
    }
}
