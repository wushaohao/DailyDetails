package designModels.Observer.weatherbyjdk;

import java.util.Observable;

/**
 * @author:wuhao
 * @description:目标类
 * @date:18/8/12
 */
public class WeatherConcreteSubject extends Observable {
    /**
     * 天气情况内容
     */

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        // 通知所有的观察者
        // 在使用Java中的Observer模式时 必须要有这行
        this.setChanged();
        // 然后主动通知 推模式
        this.notifyObservers(content);

        //拉模型
        //this.notifyObservers();

    }
}
