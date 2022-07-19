package designModels.Observer.weather;

/**
 * @author:wuhao
 * @description:测试类
 * @date:18/8/12
 */
public class Client {
    public static void main(String[] args) {
        //1.创建目标
        WeatherConcreteSubject weatherConcreteSubject = new WeatherConcreteSubject();

        //2.创建观察者
        WeatherConcreteObserver observerGirlF = new WeatherConcreteObserver();
        observerGirlF.setObserverName("GirlFriend");
        observerGirlF.setRemindThing("first date...");

        WeatherConcreteObserver observerMom = new WeatherConcreteObserver();
        observerMom.setObserverName("Mom");
        observerMom.setRemindThing("shopping...");

        //3.注册观察者
        weatherConcreteSubject.attach(observerGirlF);
        weatherConcreteSubject.attach(observerMom);

        //4.目标发布天气
        weatherConcreteSubject.setWeatherContent("tomorrow's weather is sunny");
    }
}
