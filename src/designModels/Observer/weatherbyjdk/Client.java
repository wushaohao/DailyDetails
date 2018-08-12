package designModels.Observer.weatherbyjdk;

/**
 * @author:wuhao
 * @description:
 * @date:18/8/12
 */
public class Client {
    public static void main(String[] args) {
        // 创建天气作为一个目标 也就是被观察者
        WeatherConcreteSubject subject = new WeatherConcreteSubject();

        // 创建观察者
        WeatherConcreteObserver girlF = new WeatherConcreteObserver();
        girlF.setObserverName("girlFriend");

        WeatherConcreteObserver mom = new WeatherConcreteObserver();
        mom.setObserverName("mom");

        // 添加/注册 订阅者
        subject.addObserver(girlF);
        subject.addObserver(mom);

        // 目标更新天气情况
        subject.setContent("Sunny,temp 28");

    }
}
