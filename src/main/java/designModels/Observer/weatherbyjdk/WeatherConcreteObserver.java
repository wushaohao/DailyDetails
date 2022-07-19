package designModels.Observer.weatherbyjdk;

import java.util.Observable;
import java.util.Observer;

/**
 * @author:wuhao
 * @description:观察者实现类
 * @date:18/8/12
 */
public class WeatherConcreteObserver implements Observer {

    //观察者名称变量

    private String observerName;

    public String getObserverName() {
        return observerName;
    }

    public void setObserverName(String observerName) {
        this.observerName = observerName;
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        // 推
        System.out.println(observerName + "收到了消息,目标是推送过来的");

        // 拉
        System.out.println(observerName + "收到了消息,主动到目标对象中拉取,内容是:" + ((WeatherConcreteSubject) o).getContent());

    }
}
