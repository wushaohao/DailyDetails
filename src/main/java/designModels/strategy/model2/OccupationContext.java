package designModels.strategy.model2;

/**
 * @author:wuhao
 * @description:策略上下文
 * @date:2019/12/31
 */
public class OccupationContext {
    public void occupationWestOfSichuan(String msg) {
        IOccupationStrategyWestOfSiChuan strategy = new UpperStrategy();
        try {
            strategy.occupationWestOfSiChuan(msg);
        } catch (Exception e) {
            // 上上计策有问题行不通之后，用中计策
            strategy = new MiddleStrategy();
            strategy.occupationWestOfSiChuan(msg);
        }
    }
}
