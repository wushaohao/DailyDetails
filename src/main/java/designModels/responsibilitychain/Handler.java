package designModels.responsibilitychain;

/**
 * @author:wuhao
 * @description:Handler抽象类
 * @date:18/7/25
 */
public abstract class Handler {
    /**
     * 能处理的级别
     */
    private int level;

    /**
     * 责任传递下一个责任人
     */
    private Handler nextHandler;

    /**
     * 每个人生命自己能处理的级别
     */
    public Handler(int level) {
        this.level = level;
    }

    public final void HandMessage(IWoman woman) {
        if (woman.getType() == this.level) {
            this.response(woman);
        } else {
            if (this.nextHandler != null) {
                this.nextHandler.response(woman);
            } else {
                System.out.println("---------没有接受请求处理的handler,不做处理了----------");
            }
        }
    }

    public void setNext(Handler _handler) {
        this.nextHandler = _handler;
    }

    public abstract void response(IWoman woman);
}
