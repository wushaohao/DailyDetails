package designModels.Observer.weatherbycondition;

/**
 * @author:wuhao
 * @description:观察者
 * @date:18/8/12
 */
public class WeatherConcreteObserver implements Observer {
    // 观察者名称
    private String observerName;

    // 天气内容
    private String weatherContext;

    //提醒时间
    private String remindThing;

    public String getWeatherContext() {
        return weatherContext;
    }

    public void setWeatherContext(String weatherContext) {
        this.weatherContext = weatherContext;
    }

    public String getRemindThing() {
        return remindThing;
    }

    public void setRemindThing(String remindThing) {
        this.remindThing = remindThing;
    }

    /**
     * 定义一个更新接口方法给那些在目标发生变化的时候被通知的观察者对象使用
     *
     * @param subject
     */
    @Override
    public void update(WeatherSubject subject) {
        weatherContext = ((WeatherConcreteSubject) subject).getWeatherContent();
        System.out.println(observerName + "收到了" + weatherContext + "," + remindThing);
    }

    /**
     * 设置观察者名称
     *
     * @param observerName
     */
    @Override
    public void setObserverName(String observerName) {
        this.observerName = observerName;
    }

    /**
     * 获取观察者名称
     *
     * @return
     */
    @Override
    public String getObserverName() {
        return observerName;
    }
}
