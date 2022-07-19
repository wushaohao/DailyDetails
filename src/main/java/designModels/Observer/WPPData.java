package designModels.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhao on 17/5/27.
 */
public class WPPData implements Observable {

    private String message;

    public String getMessage() {
        return message;
    }

    private List<Observer> observers = new ArrayList<>();


    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
        System.out.println("User " + observer.getName() + " attention to WeChat!");
        System.out.println("------------------------");
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
        System.out.println("User " + observer.getName() + " no attention to us!");
        System.out.println("------------------------");
    }

    @Override
    public void updateTerminals() {
        for (Observer o : observers) {
            o.dataUpdate(this);
        }
    }

    @Override
    public void notifyObserver() {
        updateTerminals();
    }
}
