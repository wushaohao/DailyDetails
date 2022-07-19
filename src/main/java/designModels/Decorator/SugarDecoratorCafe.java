package designModels.Decorator;

/**
 * Created by wuhao on 17/5/24.
 */
public class SugarDecoratorCafe extends Decorators {
    public SugarDecoratorCafe(Cafe cafe) {
        super(cafe);
    }

    @Override
    public void getCafe() {
        super.getCafe();
        this.addSugar();
    }

    public void addSugar() {
        System.out.println("add sugar..");
    }
}
