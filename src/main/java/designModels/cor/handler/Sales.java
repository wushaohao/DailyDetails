package designModels.cor.handler;

/**
 * @author:wuhao
 * @description:销售类
 * @date:18/7/29
 */
public class Sales extends PreHandler {

    @Override
    public void handle(float discount) {
        if (discount < 0.05) {
            System.out.format("%s处理了折扣请求%.2f%n", this.getClass().getSimpleName(), discount);
        } else {
            preHandler.handle(discount);
        }
    }
}
