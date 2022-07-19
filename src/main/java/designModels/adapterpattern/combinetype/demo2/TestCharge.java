package designModels.adapterpattern.combinetype.demo2;

/**
 * @author:wuhao
 * @description:客户端测试类
 * @date:2019-08-06；
 */
public class TestCharge {
    public static void main(String[] args) {
        ChargeAdapter chargeAdapter = new ChargeAdapter(new AndroidCharger());

        IphoneX iphoneX = new IphoneX();

        iphoneX.setLightningInterface(chargeAdapter);

        iphoneX.chargeWithLightning();
    }
}
