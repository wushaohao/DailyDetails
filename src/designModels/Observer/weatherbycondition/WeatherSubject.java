package designModels.Observer.weatherbycondition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wuhao
 * @description:目标类
 * @date:18/8/12
 */
public abstract class WeatherSubject {
    /**
     * 用来保存注册的观察者
     */

    protected List<Observer> observerList = new ArrayList<>();

    /**
     * 注册观察者
     * @param observer
     */
    public void attach(Observer observer) {
        observerList.add(observer);
    }

    /**
     * 删除指定的观察者
     * @param observer
     */
    public void deatch(Observer observer) {
        observerList.remove(observer);
    }

    /**
     * 由子类去实现
     */
    protected abstract void notifyObservers() ;
}
