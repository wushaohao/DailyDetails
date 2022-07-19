package designModels.strategy.model1;

/**
 * @author:wuhao
 * @description:策略上下文
 * @date:2019/12/31
 */
public class StrategyContext {
    /**
     * 持有一个策略的实现引用
     */
    private IStrategy strategy;

    /**
     * 使用构造器注入具体实现类
     *
     * @param strategy
     */
    public StrategyContext(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void contextMethod() {
        //调用策略实现方法
        strategy.algorithmMethod();
    }
}
