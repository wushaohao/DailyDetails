package designModels.strategy.model3;

import lombok.Data;

/**
 * @author:wuhao
 * @description:账户实现类2
 * @date:2019/12/31
 */
@Data
public class AccountPay2 implements PayStrategy {
    private String account;

    public AccountPay2(String account) {
        this.account = account;
    }

    @Override
    public void pay(PayContext ctx) {
        System.out.println("现在给：" + ctx.getUserName() + "的账户：" + getAccount() + " 支付工资：" + ctx.getMoney() + " 元！");
    }
}
