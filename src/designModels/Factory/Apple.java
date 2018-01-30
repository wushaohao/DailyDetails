package designModels.Factory;

/**
 * Created by wuhao on 17/5/1.
 */
public class Apple implements Fruit {
    @Override
    public void plant() {
        System.out.println("i am apple,plant...");
    }

    @Override
    public void grow() {
        System.out.println("i am apple,grow...");

    }

    @Override
    public void harvest() {
        System.out.println("i am apple,harvest..");

    }
}
