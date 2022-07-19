package designModels.cor.handler;

/**
 * @author:wuhao
 * @description:CEO类
 * @date:18/7/29
 */
public class CEO extends PreHandler {
    @Override
    public void handle(float discount) {
        if (0.15 < discount && discount < 0.2) {
            System.out.format("%s处理了折扣请求%.2f%n", this.getClass().getSimpleName(), discount);
        } else {
            System.out.println("处理不了...不卖了");
        }
    }
}
