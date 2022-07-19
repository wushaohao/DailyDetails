package designModels.cor.handler;

/**
 * @author:wuhao
 * @description:经理类
 * @date:18/7/29
 */
public class Manager extends PreHandler {

    @Override
    public void handle(float discount) {
        if (0.05 < discount && discount < 0.1) {
            System.out.format("%s处理了折扣请求%.2f%n", this.getClass().getSimpleName(), discount);
        } else {
            /**
             * 继续传递处理的handler类
             */
            preHandler.handle(discount);
        }
    }
}
