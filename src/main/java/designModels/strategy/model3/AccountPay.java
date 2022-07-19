package designModels.strategy.model3;

/**
 * @author:wuhao
 * @description:账户实现类
 * @date:2019/12/31
 */
public class AccountPay implements PayStrategy {
    @Override
    public void pay(PayContext ctx) {
        PayContextWithAccount ctxAccount = (PayContextWithAccount) ctx;
        System.out.println("现在给：" + ctxAccount.getUserName() + "的账户：" + ctxAccount.getAccount() + " 支付工资：" + ctxAccount.getMoney() + " 元！");
    }
}
