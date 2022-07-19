package designModels.strategy.model3;

import lombok.Data;

/**
 * @author:wuhao
 * @description:上下文扩展实现类
 * @date:2019/12/31
 */
@Data
public class PayContextWithAccount extends PayContext {
    private String account;

    public PayContextWithAccount(String userName, double money, PayStrategy payStrategy, String account) {
        super(userName, money, payStrategy);
        this.account = account;
    }
}
