package designModels.adapterpattern.combinetype;

/**
 * @author:wuhao
 * @description:二相转三相的插座适配器
 * @date:18/8/20
 */
public class TwoPlugAdapter implements ThreePlugIf {
    private GBTwoPlugin gbTwoPlugin;

    public TwoPlugAdapter(GBTwoPlugin gbTwoPlugin) {
        this.gbTwoPlugin = gbTwoPlugin;
    }

    /**
     * 使用三相电流供电
     */
    @Override
    public void powerWithThree() {
        System.out.println("适配转换....");
        gbTwoPlugin.powerWithTwo();

    }
}
