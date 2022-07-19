package designModels.Proxy.imooc.jdkproxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author:wuhao
 * @description:
 * @date:18/8/16
 */
public class BusiHandler implements InvocationHandler {

    private Object target;

    public BusiHandler(Object target) {
        this.target = target;
    }

    @Override
    public void invoke(Object o, Method method, Object[] args) {
        //TODO : 添加业务逻辑
        try {
            method.invoke(target, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
