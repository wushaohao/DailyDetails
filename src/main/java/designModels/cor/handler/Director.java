package designModels.cor.handler;

/**
 * @author:wuhao
 * @description:总监类
 * @date:18/7/29
 */
public class Director extends PreHandler {

    @Override
    public void handle(float discount) {
        if (0.1 < discount && discount < 0.15) {
            System.out.format("%s处理了折扣请求%.4f%n", this.getClass().getSimpleName(), discount);
        } else {
            preHandler.handle(discount);
        }
    }
}
