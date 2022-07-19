package designModels.Observer.weather;


/**
 * @author:wuhao
 * @description:
 * @date:18/8/12
 */
public class WeatherConcreteObserver implements Observer {
    /**
     * 观察者名字 是谁收到了这个信息 女朋友还是老妈
     */
    private String observerName;

    /**
     * 天气内容 这个消息从目标出获取
     */
    private String weatherContent;

    /**
     * 提醒的内容 女朋友提醒约会 老妈则提醒购物
     */
    private String remindThing;

    public String getObserverName() {
        return observerName;
    }

    public void setObserverName(String observerName) {
        this.observerName = observerName;
    }

    public String getWeatherContent() {
        return weatherContent;
    }

    public void setWeatherContent(String weatherContent) {
        this.weatherContent = weatherContent;
    }

    public String getRemindThing() {
        return remindThing;
    }

    public void setRemindThing(String remindThing) {
        this.remindThing = remindThing;
    }

    /**
     * 更新的接口
     *
     * @param subject 获取目标累的状态同步到观察者状态中
     */
    @Override
    public void update(WeatherSubject subject) {
        // 获取目标类的状态同步到观察者的状态中
        weatherContent = ((WeatherConcreteSubject) subject).getWeatherContent();
        System.out.println(observerName + "收到了" + weatherContent + "," + remindThing);
    }
}
