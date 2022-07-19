package completable.compare;

/**
 * @author:wuhao
 * @description:折扣类
 * @date:2019/12/12
 */
public class Discount {
  public enum Code {
    NONE(0),
    SILVER(5),
    GOLD(10),
    PLATINUM(15),
    DIAMOND(20);

    int percentage;

    Code(int percentage) {
      this.percentage = percentage;
    }
  }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + apply(quote.getPrice(), quote.getCode());
    }

    public static double apply(double price, Code code) {
        delay();
        return price * (100 - code.percentage) / 100;
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
