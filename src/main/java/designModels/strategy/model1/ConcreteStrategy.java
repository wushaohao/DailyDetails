package designModels.strategy.model1;

/**
 * @author:wuhao
 * @description:具体实现策略接口的实现类
 * @date:2019/12/31
 */
public class ConcreteStrategy implements IStrategy {
    /**
     * 策略定义方法
     */
    @Override
    public void algorithmMethod() {
        System.out.println("ConcreteStrategy algorithmMethod is start...");
    }
}
