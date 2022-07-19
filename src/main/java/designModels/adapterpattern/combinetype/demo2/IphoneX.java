package designModels.adapterpattern.combinetype.demo2;

/**
 * @author:wuhao
 * @description:苹果手机
 * @date:2019-08-06
 */
public class IphoneX implements LightningInterface {
    private LightningInterface lightningInterface;

    public IphoneX() {
    }

    public IphoneX(LightningInterface lightningInterface) {
        this.lightningInterface = lightningInterface;
    }

    public LightningInterface getLightningInterface() {
        return lightningInterface;
    }

    public void setLightningInterface(LightningInterface lightningInterface) {
        this.lightningInterface = lightningInterface;
    }

    @Override
    public void chargeWithLightning() {
        System.out.println("开始我的iphoneX手机充电");
        lightningInterface.chargeWithLightning();
        System.out.println("iphoneX手机充电结束");
    }
}
