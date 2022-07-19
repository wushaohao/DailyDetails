package designModels.Proxy.imooc.jdkproxy;

import java.lang.reflect.Method;

/**
 * @author:wuhao
 * @description:业务逻辑处理类
 * @date:18/8/16
 */
public interface InvocationHandler {
    public void invoke(Object o, Method method, Object[] args);
}
