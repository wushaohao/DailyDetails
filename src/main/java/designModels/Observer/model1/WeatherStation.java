package designModels.Observer.model1;

/**
 * @author wuhao
 * @date 17/6/9
 */
public class WeatherStation {
    public static void main(String[] args) {
        // 建立WeatherData对象
        WeatherData weatherData = new WeatherData();

        // 建立目前状况布告板
        CurrentConditionDisplay currentDisplay = new CurrentConditionDisplay(weatherData);

        // 模拟新的气象测量
        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);

        // 删除订阅者
        weatherData.removeObserver(currentDisplay);
        weatherData.setMeasurements(78, 90, 29.2f);

        // 虚拟机可用最大处理器数目 8
        System.out.println("jvm max available processor:" + Runtime.getRuntime().availableProcessors());
        // jvm最大内存量,jvm将尝试使用(返回字节数) 3817865216kb  1M＝1024kb
        System.out.println("内存最大数是:" + Runtime.getRuntime().maxMemory());


    }
}
