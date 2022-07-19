package designModels.adapterpattern.combinetype.demo2;

/**
 * @author:wuhao
 * @description:安卓充电手机
 * @date:2019-08-06
 */
public class AndroidCharger implements TypeCInterface {

    @Override
    public void chargeWithTypeC() {
        System.out.println("使用typeC充电...");
    }
}
