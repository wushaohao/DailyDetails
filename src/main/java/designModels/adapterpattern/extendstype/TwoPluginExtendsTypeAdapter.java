package designModels.adapterpattern.extendstype;

import designModels.adapterpattern.combinetype.GBTwoPlugin;
import designModels.adapterpattern.combinetype.ThreePlugIf;

/**
 * @author:wuhao
 * @description:继承方式实现适配器
 * @date:18/8/20
 */
public class TwoPluginExtendsTypeAdapter extends GBTwoPlugin implements ThreePlugIf {
    /**
     * 使用三相电流供电
     */
    @Override
    public void powerWithThree() {
        System.out.println("采用继承方式实现适配器..");
        this.powerWithTwo();
    }
}
