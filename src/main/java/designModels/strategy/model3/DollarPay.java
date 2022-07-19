package designModels.strategy.model3;

// 美金支付策略
public class DollarPay implements PayStrategy {
    @Override
    public void pay(PayContext ctx) {
        System.out.println("现在给：" + ctx.getUserName() + " 美金支付 " + ctx.getMoney() + "dollar !");
    }
}
