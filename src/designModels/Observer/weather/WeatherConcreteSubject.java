package designModels.Observer.weather;

/**
 * @author:wuhao
 * @description:
 * @date:18/8/12
 */
public class WeatherConcreteSubject extends WeatherSubject{
    // 天气内容信息
    private String weatherContent;

    public String getWeatherContent() {
        return weatherContent;
    }

    public void setWeatherContent(String weatherContent) {
        this.weatherContent = weatherContent;
        this.notifyObservers();
    }
}
