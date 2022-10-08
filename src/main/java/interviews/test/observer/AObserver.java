package interviews.test.observer;

import java.util.ArrayList;

/**
 * @author:wuhao
 * @description:实现
 * @date:2022/8/8
 */
public class AObserver implements Subject {

    public ArrayList<Observer> observers = new ArrayList();


    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void callObserver() {
        for (Observer o : observers) {
            o.updateInfo();
        }

    }
}
