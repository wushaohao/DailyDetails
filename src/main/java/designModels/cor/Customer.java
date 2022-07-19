package designModels.cor;

import designModels.cor.handler.HandlerFactory;
import designModels.cor.handler.PreHandler;

import java.util.Random;

/**
 * @author:wuhao
 * @description:消费者
 * @date:18/7/29
 */
public class Customer {
    private PreHandler preHandler;

    public void setPreHandler(PreHandler preHandler) {
        this.preHandler = preHandler;
    }

    public void request(float discount) {
        preHandler.handle(discount);
    }

    public static void main(String[] args) {
        Customer customer = new Customer();

        Random random = new Random();

        customer.setPreHandler(HandlerFactory.createHandler());
        for (int i = 1; i < 100; i++) {
            System.out.print(i + ":");
            customer.request(random.nextFloat());
        }
    }
}
