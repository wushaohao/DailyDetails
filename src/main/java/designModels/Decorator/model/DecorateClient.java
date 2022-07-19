package designModels.Decorator.model;

/**
 * @author:wuhao
 * @description:装饰者测试类
 * @date:2019/12/31
 */
public class DecorateClient {
    public static void main(String[] args) {
        //被装饰者
        Man man = new Man();

        //装饰者
        ManDecoratorA md1 = new ManDecoratorA();
        ManDecoratorB md2 = new ManDecoratorB();

        md1.setPerson(man);
        md2.setPerson(man);
        md1.eat();
        md2.eat();
    }
}
