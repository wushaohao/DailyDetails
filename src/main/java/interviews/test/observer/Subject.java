package interviews.test.observer;

/**
 * @author:wuhao
 * @description:观察者接口
 * @date:2022/8/8
 */
public interface Subject {
    void addObserver(Observer o);

    void callObserver();
}
