package completable.compare;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author:wuhao
 * @description:测试客户端调用
 * @date:2019/12/12
 */
public class Client {
  public static void main(String[] args) {
    //
    Shop shop = new Shop("BestShop");
    long start = System.currentTimeMillis();

    Future<Double> future = shop.getPriceSync("My Favorite");
    long invocationTime = System.currentTimeMillis() - start;
    System.out.println("调用接口时间:" + invocationTime + "毫秒!");

    doSomethingElse();

    try {
      double price = future.get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    long retrievalTime = System.currentTimeMillis() - start;
    System.out.println("返回价格消耗时间：" + retrievalTime + "毫秒");
  }

  public static void doSomethingElse() {
    System.out.println("做其他事情!");
  }

}
