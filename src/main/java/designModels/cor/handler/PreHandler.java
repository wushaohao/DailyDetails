package designModels.cor.handler;

/**
 * @author:wuhao
 * @description:预置抽象处理类
 * @date:18/7/29
 */
public abstract class PreHandler {
    /**
     * 用于子类来继承
     */
    protected PreHandler preHandler;

    public void setPreHandler(PreHandler preHandler) {
        this.preHandler = preHandler;
    }

    /**
     * 具体的处理方法
     *
     * @param discount
     */
    public abstract void handle(float discount);

}
