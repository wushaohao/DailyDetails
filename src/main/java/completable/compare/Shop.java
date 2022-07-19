package completable.compare;

import lombok.Data;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author:wuhao
 * @description:bean类
 * @date:2019/12/12
 */
@Data
public class Shop {

  private String name;
  private Random random = new Random();

  public Shop(String name) {
    this.name = name;
  }

  public double getPrice(String product) {
    return calculatePrice(product);
  }

    public String getPrice2(String product){
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("s%:%.2f:%s", name, price, code);
    }

  /** 模拟延迟 */
  public static void delay() {
    try {
      Thread.sleep(1000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private double calculatePrice(String product) {
    delay();
    return random.nextDouble() * product.charAt(0) + product.charAt(1);
  }

    /**
     * 异步获取价格
      */
  public Future<Double> getPriceSync(String product) {
    CompletableFuture<Double> future = new CompletableFuture<>();
    new Thread(()->{
        double price=calculatePrice(product);
        future.complete(price);
    }).start();
    return future;
  }

}
