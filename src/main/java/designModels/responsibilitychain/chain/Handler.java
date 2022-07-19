package designModels.responsibilitychain.chain;

/**
 * @author:wuhao
 * @description:Handler抽象类
 * @date:18/8/17
 */
public abstract class Handler {
    private Handler successor;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public void execute() {
        handleProcess();
        if (successor != null) {
            successor.handleProcess();
        }

    }

    protected abstract void handleProcess();
}
