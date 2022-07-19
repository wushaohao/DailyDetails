package designModels.responsibilitychain.chain.better;

import java.util.Arrays;
import java.util.List;

/**
 * @author:wuhao
 * @description:客户端测试类
 * @date:18/8/17
 */
public class ChainClient {
    static class HandlerProcessA extends ChainHandler {

        @Override
        protected void handProcess() {
            System.out.println("handlerA");
        }
    }

    static class HandlerProcessB extends ChainHandler {
        @Override
        protected void handProcess() {
            System.out.println("handlerB");

        }
    }

    static class HandlerProcessC extends ChainHandler {
        @Override
        protected void handProcess() {
            System.out.println("handlerC");

        }
    }

    public static void main(String[] args) {
        List<ChainHandler> chainHandlers = Arrays.asList(new HandlerProcessA(), new HandlerProcessB(), new HandlerProcessC());

        Chain chain = new Chain(chainHandlers);

        chain.process();
    }
}
