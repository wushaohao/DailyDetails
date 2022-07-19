package designModels.strategy.model2;

/**
 * @author:wuhao
 * @description:测试客户端
 * @date:2019/12/31
 */
public class StrategtClient2 {
    public static void main(String[] args) {
        //
        OccupationContext context = new OccupationContext();

        context.occupationWestOfSichuan("拿下四川");
        System.out.println("=========================");
        // 这个人人有赏，让士兵有动力啊
        context.occupationWestOfSichuan("拿下西川之后，人人有赏！");
    }
}
