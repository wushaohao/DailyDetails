package designModels.adapterpattern.combinetype.demo2;

/**
 * @author:wuhao
 * @description:充电器适配器类
 * @date:2019-08-06
 */
public class ChargeAdapter implements LightningInterface {
    private TypeCInterface typeCInterface;

    public ChargeAdapter() {
    }

    public ChargeAdapter(TypeCInterface typeCInterface) {
        this.typeCInterface = typeCInterface;
    }

    @Override
    public void chargeWithLightning() {
        typeCInterface.chargeWithTypeC();
    }
}
