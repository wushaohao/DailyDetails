package designModels.strategy.model3;

/**
 * @author:wuhao
 * @description:策略客户端
 * @date:2019/12/31
 */
public class StrategtClient3 {
    public static void main(String[] args) {
        //创建具体的支付策略
        PayStrategy rmbStrategy = new RMBPay();
        PayStrategy dollarStrategy = new DollarPay();
        // 准备小王的支付上下文
        PayContext ctx = new PayContext("小王", 30000, rmbStrategy);
        // 向小王支付工资
        ctx.pay();

        // 准备Jack的支付上下文
        ctx = new PayContext("jack", 10000, dollarStrategy);
        // 向Jack支付工资
        ctx.pay();

        //创建支付到银行账户的支付策略
        PayStrategy accountStrategy = new AccountPay();
        //准备带有银行账户的上下文
        ctx = new PayContextWithAccount("小张", 40000, accountStrategy, "1234567890");
        //向小张的账户支付
        ctx.pay();

        PayStrategy accountStrategy2 = new AccountPay2("1234567890");
        //准备上下文
        ctx = new PayContext("小张", 40000, accountStrategy2);
        //向小张的账户支付
        ctx.pay();

    }
}
