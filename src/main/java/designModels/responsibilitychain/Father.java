package designModels.responsibilitychain;

/**
 * @author:wuhao
 * @description:父亲处理类
 * @date:18/7/25
 */
public class Father extends Handler {

    /**
     * 每个人生命自己能处理的级别
     */
    public Father() {
        super(1);
    }

    @Override
    public void response(IWoman woman) {
        System.out.println("----女儿向父亲请示-----");
        System.out.println(woman.getResponse());
        System.out.println("父亲的回答是:同意");
    }
}
