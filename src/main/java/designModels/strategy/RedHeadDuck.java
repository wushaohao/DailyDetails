package designModels.strategy;

/**
 * @author:wuhao
 * @description:子类
 * @date:18/8/17
 */
public class RedHeadDuck extends Duck {
    /**
     * 用于子类来继承 实现不同的性能
     */
    @Override
    public void display() {
        System.out.println("我的头是红色的..");
    }
}
