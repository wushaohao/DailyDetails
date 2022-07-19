package designModels.Factory;

/**
 * Created by wuhao on 17/5/1.
 */
public class Grape implements Fruit {
    @Override
    public void plant() {
        System.out.println("i am grape,plant...");
    }

    @Override
    public void grow() {
        System.out.println("i am grape,grow...");
    }

    @Override
    public void harvest() {
        System.out.println("i am grape,harvest...");
    }
}
