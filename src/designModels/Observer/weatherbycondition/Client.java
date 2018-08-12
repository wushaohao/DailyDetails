package designModels.Observer.weatherbycondition;

/**
 * @author:wuhao
 * @description:测试类
 * @date:18/8/12
 */
public class Client {
    public static void main(String[] args) {
        // 1.创建目标
        WeatherConcreteSubject subject = new WeatherConcreteSubject();

        // 2.创建观察者
        WeatherConcreteObserver girlF = new WeatherConcreteObserver();
        girlF.setObserverName("girlFriend");
        girlF.setRemindThing("rain stay home");

        WeatherConcreteObserver mom = new WeatherConcreteObserver();
        mom.setObserverName("mom");
        mom.setRemindThing("stay home");

        // 3.注册观察者
        subject.attach(girlF);
        subject.attach(mom);

        // 4.目标发布天气
//        subject.setWeatherContent("rain");

        subject.setWeatherContent("snow");
    }
}
