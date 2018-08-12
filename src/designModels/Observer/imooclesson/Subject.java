package designModels.Observer.imooclesson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wuhao
 * @description:目标对象,它知道观察它的观察者,并提供注册(添加)和删除观察者的接口
 * @date:18/8/12
 */
public class Subject {
    /**
     * 用来保存注册的观察者对象
     */
    private List<Observer> observers = new ArrayList<>();

    /**
     *
     * @param observer
     */
    public void attach(Observer observer) {
        observers.add(observer);
    }

    /**
     * 删除集合中指定的观察者
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
