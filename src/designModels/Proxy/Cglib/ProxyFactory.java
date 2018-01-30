package designModels.Proxy.Cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *
 * @author wuhao
 * @date 17/5/24
 */
public class ProxyFactory implements MethodInterceptor{
    /**
     * 维护目标对象
      */
    private Object target;

    public ProxyFactory(Object target){
        this.target=target;
    }

    /**
     * 给目标表创建一个代理对象
     */
    public Object getProxyInstance(){
        // 工具类
        Enhancer enhancer=new Enhancer();
        // 设置父类
        enhancer.setSuperclass(target.getClass());
        // 设置回调函数
        enhancer.setCallback(this);
        // 创建子类(代理对象)
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始事务!");
        //执行目标对象的方法
        Object returnValue=method.invoke(target,objects);
        System.out.println("提交事务!");
        return returnValue;
    }
}
