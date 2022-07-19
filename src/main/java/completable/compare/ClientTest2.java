package completable.compare;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.stream.Collectors.toList;

/**
 * @author:wuhao
 * @description:测试2
 * @date:2019/12/12
 */
public class ClientTest2 {
  private ExecutorService executor = Executors.newCachedThreadPool();

    private List<Shop> shops = Arrays.asList(new Shop("Sunjin.org"),
            new Shop("加瓦匠"),
            new Shop("京东商城"),
            new Shop("天猫超市"));

  public  List<String> findPrice(String product) {
    return shops.parallelStream().map(shop -> String.format("%s的价格是%.2f",shop.getName(),shop.getPrice(product))).collect(toList());
  }

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    System.out.println(new ClientTest2().findPrice("java8实战"));
    long duration = System.currentTimeMillis() - start;
    System.out.println("总消耗时间:" + duration + "毫秒!");

      System.out.println(new ClientTest2().findPrice2("java8实战"));
      long duration2 = System.currentTimeMillis() - start;
      System.out.println("CompletableFuture总消耗时间:" + duration2 + "毫秒!");


  }

  public List<String> findPrice2(String product) {
    List<CompletableFuture<String>> priceFuture =
        shops.stream()
            .map(
                shop ->
                    CompletableFuture.supplyAsync(
                        () -> shop.getName() + "的价格是" + shop.getPrice(product))).collect(toList());

    return priceFuture.stream().map(CompletableFuture::join).collect(toList());
  }


  public  List<String> findPrice3(String product) {
    return shops.stream()
            //获取原始报价
        .map(shop -> shop.getPrice2(product))
            //解析报价字符串
        .map(Quote::parse)
            //调用折扣服务应用报价折扣
        .map(Discount::applyDiscount)
        .collect(toList());
  }

  public List<String> findPrice4(String product) {
    List<CompletableFuture<String>> priceFuture =
        shops.stream()
            .map(shop -> CompletableFuture.supplyAsync(
                    // 异步获取价格
                    () -> shop.getPrice2(product), executor))
                // 获取到价格后对价格解析
            .map(future -> future.thenAccept(Quote::parse))
            .map(
                future ->
                    future.thenCompose(
                        quote ->
                                //另一个异步任务构造异步应用报价
                            CompletableFuture.supplyAsync(
                                () -> Discount.applyDiscount(quote), executor)))
            .collect(toList());

    return priceFuture.stream()
            // join操作和get操作一样 等待所有异步操作的结果
            .map(CompletableFuture::join)
            .collect(toList());
  }

  public List<Double> findPrince(String product) {
    List<CompletableFuture<Double>> priceFuture =
        shops.stream()
                //异步获取价格
            .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
            .map(
                future ->
                    future.thenCombine(
                            //异步获取折扣率
                        CompletableFuture.supplyAsync(() -> Discount.getRate(), executor),
                        // 将两个异步任务的结果合并
                        (price, rate) -> price * rate))
            .collect(toList());
    return priceFuture.stream().map(CompletableFuture::join).collect(toList());
  }
}


