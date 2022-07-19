package dynamicproxy.model;

import java.lang.reflect.Proxy;

/**
 * @author:wuhao
 * @description:Java代理类
 * @date:2020/3/2
 */
public class JavaDynamicProxy {

    public static void main(String[] args) {
        JavaDeveloper zack = new JavaDeveloper("Zack");
        // invocationHandler参数的作用就是当代理对象的原本方法被调用的时候,会绑定执行一个方法,这个方法就是invocaHandler里面定义的内容,同时会替代原本方法的结果返回
        Developer zackProxy =
                (Developer)
                        Proxy.newProxyInstance(
                                zack.getClass().getClassLoader(),
                                zack.getClass().getInterfaces(),
                                // invocationHandler接受3个参数:proxy-代理后实例对象,method-对象被调用方法,args-调用时参数
                                ((proxy, method, args1) -> {
                                    if (method.getName().equals("code")) {
                                        System.out.println("Zack is praying for code!");
                                        // invoke的俩参数,如果是proxy会循环调用导致程序出错,当该代理对象被调用的时候会在次触发invocationHandler,而invocationHandler又一次调用proxy
                                        return method.invoke(zack, args1);
                                    }

                                    if (method.getName().equals("debug")) {
                                        System.out.println("Zack have no bug!No need to debug!");
                                        return null;
                                    }
                                    return null;
                                }));
        zackProxy.code();
        zackProxy.debug();
    }
}
