package designModels.strategy.model2;

/**
 * @author:wuhao
 * @description:实现策略接口的实现类
 * @date:2019/12/31
 */
public class UpperStrategy implements IOccupationStrategyWestOfSiChuan {
    @Override
    public void occupationWestOfSiChuan(String msg) {
        if (msg == null || msg.length() < 5) {
            System.out.println("由于计划泄露，上上计策失败！");
            int i = 100 / 0;
        }
        System.out.println("挑选精兵，昼夜兼行直接偷袭成都，可以一举而定,此为上计计也!");
    }
}
