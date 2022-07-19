package designModels.responsibilitychain.chain.better;

/**
 * @author:wuhao
 * @description:抽象责任链
 * @date:18/8/17
 */
public abstract class ChainHandler {

    public void execute(Chain chain) {
        handProcess();
        chain.process();
    }

    protected abstract void handProcess();

}
