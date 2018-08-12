package designModels.Observer.imooclesson;

/**
 * @author:wuhao
 * @description:观察者接口,定义一个更新的接口给那些在目标发生改变的时候被通知的对象
 * @date:18/8/12
 */
public interface Observer {

    /**
     * 更新的接口
     * @param subject 传入目标对象,方便获取响应的目标对象的状态
     */
    public void update(Subject subject);


}
