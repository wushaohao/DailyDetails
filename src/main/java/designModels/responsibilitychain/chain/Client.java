package designModels.responsibilitychain.chain;

/**
 * @author:wuhao
 * @description:客户端测试类
 * @date:18/8/17
 */
public class Client {
    static class HandlerProcessA extends Handler {
        @Override
        protected void handleProcess() {
            System.out.println("handlerA");
        }
    }

    static class HandlerProcessB extends Handler {
        @Override
        protected void handleProcess() {
            System.out.println("handlerB");

        }
    }

    static class HandlerProcessC extends Handler {
        @Override
        protected void handleProcess() {
            System.out.println("handlerB");

        }
    }

    public static void main(String[] args) {
        Handler handlerProcessA = new HandlerProcessA();
        Handler handlerProcessB = new HandlerProcessB();
        Handler handlerProcessC = new HandlerProcessC();

        handlerProcessA.setSuccessor(handlerProcessB);
        handlerProcessB.setSuccessor(handlerProcessC);

        handlerProcessA.execute();
    }
}
