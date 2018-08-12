package designModels.Observer.weatherbycondition;

/**
 * @author:wuhao
 * @description:目标实现类
 * @date:18/8/12
 */
public class WeatherConcreteSubject extends WeatherSubject{

    /**
     * 目标对象状态
     */
    private String weatherContent;

    public String getWeatherContent() {
        return weatherContent;
    }

    public void setWeatherContent(String weatherContent) {
        this.weatherContent = weatherContent;
        this.notifyObservers();

    }

    /**
     * 由子类去实现
     */
    @Override
    protected void notifyObservers() {
        for (Observer o:observerList) {
            // 根据天气规则推送信息
            if ("rain".equals(this.getWeatherContent())) {
                if ("girlFriend".equals(o.getObserverName())) {
                    o.update(this);
                }
                if ("mom".equals(o.getObserverName())) {
                    o.update(this);
                }
            }

            if ("snow".equals(this.getWeatherContent())) {
                if ("mom".equals(o.getObserverName())) {
                    o.update(this);
                }
            }

        }

    }
}
