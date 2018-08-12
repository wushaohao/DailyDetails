package designModels.Observer.weather;


import java.util.ArrayList;
import java.util.List;

/**
 * @author:wuhao
 * @description:
 * @date:18/8/12
 */
public class WeatherSubject {
    /**
     * 用来保存注册的观察者对象
     */
    private List<Observer> observers = new ArrayList<>();

    /**
     * 把订阅天气的人添加到订阅列表中
     * @param observer
     */
    public void attach(Observer observer) {
        observers.add(observer);
    }

    /**
     * 删除集合中指定的订阅天气的人
     * @param observer
     */
    public void deatch(Observer observer) {
        observers.remove(observer);
    }

    /**
     * 通知所有注册的观察者对象
     */
    protected void notifyObservers() {
        for (Observer o:observers) {
            o.update(this);
        }
    }
}
