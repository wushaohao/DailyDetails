package designModels.Decorator.model;

/**
 * @author:wuhao
 * @description:具体装饰者A
 * @date:2019/12/31
 */
public class ManDecoratorA extends Decorator {
    @Override
    public void eat() {
        super.eat();
        reEat();
        System.out.println("ManDecoratorA类");
    }

    public void reEat() {
        System.out.println("再吃一顿吧...");
    }
}
