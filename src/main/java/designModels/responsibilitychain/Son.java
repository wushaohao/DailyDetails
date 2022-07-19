package designModels.responsibilitychain;

/**
 * @author:wuhao
 * @description:儿子类
 * @date:18/7/25
 */
public class Son extends Handler {
    /**
     * 每个人生命自己能处理的级别
     */
    public Son() {
        super(3);
    }

    @Override
    public void response(IWoman woman) {
        System.out.println("----母亲向儿子请示-----");
        System.out.println(woman.getResponse());
        System.out.println("儿子的回答是:同意");
    }
}
