package designModels.strategy.model1;

/**
 * @author:wuhao
 * @description:测试客户端
 * @date:2019/12/31
 */
public class StrategyClient {
    public static void main(String[] args) {
        // 创建具体的策略实现
        IStrategy strategy = new ConcreteStrategy();
        IStrategy strategy2 = new ConcreteStrategy2();

        // 在创建策略上下文的同时，将具体的策略实现对象注入到策略上下文当中
        StrategyContext context = new StrategyContext(strategy);
        StrategyContext context2 = new StrategyContext(strategy2);

        // 调用上下文对象的方法来完成对具体策略实现的回调
        context.contextMethod();
        context2.contextMethod();
    }
}
