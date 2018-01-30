package designModels.Observer.model1;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者 实现了订阅者的接口
 * @author wuhao
 * @date 17/6/9
 */

public class WeatherData implements Subject {

    private List<Observer> lists;
    private float temp;
    private float humidity;
    private float pressure;

    public WeatherData() {
        this.lists=new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        System.out.println("register observer....");
        lists.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        System.out.println("remove observer....");
        lists.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer o:lists) {
            o.update(temp,humidity,pressure);
        }
    }

    /**
     * 当从气象站得到更新观测值时，我们通知观察者
     */
    public void measurementChanged(){
        notifyObserver();
    }

    public void setMeasurements(float temp,float humidity,float pressure){
        this.temp=temp;
        this.humidity=humidity;
        this.pressure=pressure;
        measurementChanged();
    }
}
