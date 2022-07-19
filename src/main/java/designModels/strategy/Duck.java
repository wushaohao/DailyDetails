package designModels.strategy;

/**
 * @author:wuhao
 * @description:超类
 * @date:18/8/17
 */
public abstract class Duck {

    /**
     *
     */
    public void quack() {
        System.out.println("嘎嘎嘎, 我是只小鸭子咿呀咿呀哟...");
    }

    /**
     * 用于子类来继承 实现不同的性能
     */
    public abstract void display();
}
