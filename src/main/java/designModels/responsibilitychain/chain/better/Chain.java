package designModels.responsibilitychain.chain.better;

import java.util.List;

/**
 * @author:wuhao
 * @description:责任链条
 * @date:18/8/17
 */
public class Chain {
    private List<ChainHandler> chainHandlers;

    public Chain(List<ChainHandler> chainHandlers) {
        this.chainHandlers = chainHandlers;
    }

    private int index = 0;

    public void process() {
        if (index >= chainHandlers.size()) {
            return;
        }

        chainHandlers.get(index++).execute(this);
    }
}
