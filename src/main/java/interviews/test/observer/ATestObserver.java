package interviews.test.observer;

/**
 * @author:wuhao
 * @description:实现
 * @date:2022/8/8
 */
public class ATestObserver implements Observer {
    @Override
    public void updateInfo() {
        System.out.println("ATest is said");
    }
}
