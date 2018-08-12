package designModels.Observer.weatherbycondition;

/**
 * @author:wuhao
 * @description:观察者接口
 * @date:18/8/12
 */
public interface Observer {

    /**
     * 定义一个更新接口方法给那些在目标发生变化的时候被通知的观察者对象使用
     * @param subject
     */
    public void update(WeatherSubject subject);

    /**
     * 设置观察者名称
     * @param observerName
     */
    public void setObserverName(String observerName);

    /**
     * 获取观察者名称
     * @return
     */
    public String getObserverName();
}
