package designModels.responsibilitychain;

/**
 * @author:wuhao
 * @description:丈夫类
 * @date:18/7/25
 */
public class Husband extends Handler {
    /**
     * 每个人生命自己能处理的级别
     */
    public Husband() {
        super(2);
    }

    @Override
    public void response(IWoman woman) {
        System.out.println("----妻子向丈夫请示-----");
        System.out.println(woman.getResponse());
        System.out.println("丈夫的回答是:同意");
    }
}
