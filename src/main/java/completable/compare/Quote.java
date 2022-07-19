package completable.compare;

import lombok.Data;

/**
 * @author:wuhao
 * @description:折扣类
 * @date:2019/12/12
 */
@Data
public class Quote {
  private final String shopName;
  private final double price;
  private final Discount.Code code;

  public Quote(String shopName, double price, Discount.Code code) {
    this.shopName = shopName;
    this.price = price;
    this.code = code;
  }

  public static Quote parse(String s) {
    String[] arr = s.split(":");
    return new Quote(arr[0], Double.valueOf(arr[1]), Discount.Code.valueOf(arr[2]));
  }
}
