package designModels.strategy.model3;

import lombok.Data;

/**
 * @author:wuhao
 * @description:策略上下文
 * @date:2019/12/31
 */
@Data
public class PayContext {
    private String userName;
    private double money;
    private PayStrategy payStrategy;

    public void pay() {
        payStrategy.pay(this);
    }

    public PayContext(String userName, double money, PayStrategy payStrategy) {
        this.userName = userName;
        this.money = money;
        this.payStrategy = payStrategy;
    }
}
