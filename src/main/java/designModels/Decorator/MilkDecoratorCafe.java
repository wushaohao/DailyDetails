package designModels.Decorator;

/**
 * Created by wuhao on 17/5/24.
 */
public class MilkDecoratorCafe extends Decorators {
    public MilkDecoratorCafe(Cafe cafe) {
        super(cafe);
    }

    @Override
    public void getCafe() {
        super.getCafe();
        this.addMilk();
    }

    private void addMilk() {
        System.out.println("add milk ..");
    }
}
