package designModels.Observer.model1;

/**
 * 主题： 包含订阅、删除、通知功能
 * @author wuhao
 * @date 17/6/9
 */
public interface Subject {
    /**
     * registerObserver和removeObserver都需要一个观察者作为变量，该观察者是用来注册或被删除的
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 删除
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     *  当主题状态改变时。这个方法会被调用，以通知所有的观察者
     */
    void notifyObserver();
}
