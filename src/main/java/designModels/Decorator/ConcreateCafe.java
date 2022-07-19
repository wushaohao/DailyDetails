package designModels.Decorator;

/**
 * Created by wuhao on 17/5/24.
 */
public class ConcreateCafe implements Cafe {
    @Override
    public void getCafe() {
        System.out.println("this is original coffee...");
    }
}
