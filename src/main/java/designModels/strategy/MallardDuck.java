package designModels.strategy;

/**
 * @author:wuhao
 * @description:
 * @date:18/8/17
 */
public class MallardDuck extends Duck {

    /**
     * 用于子类来继承 实现不同的性能
     */
    @Override
    public void display() {
        System.out.println("我是绿色的..");
    }
}
