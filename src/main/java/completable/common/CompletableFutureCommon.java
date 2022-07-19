package completable.common;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author:wuhao
 * @description:简单使用
 * @date:2019/12/16
 */
public class CompletableFutureCommon {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // thenCompose可以用于组合多个CompletableFuture，将前一个结果作为下一个计算的参数，它们之间存在着先后顺序
        // thenComposeAsync:在异步操作完成的时候对异步操作的结果进行一些操作，并且仍然返回CompletableFuture类型。默认使用ForkJoinPool,也可使用定义线程池
        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> "Hello")
                        .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "World"));
        System.out.println("thenCompose:" + future.get());

        CompletableFuture<Double> future1 =
                CompletableFuture.supplyAsync(() -> "100")
                        .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "100"))
                        .thenCompose(s -> CompletableFuture.supplyAsync(() -> Double.parseDouble(s)));
        System.out.println("thenCompose:" + future1.get());

        // thenAcceptBoth跟thenCombine类似，但是返回CompletableFuture<Void>类型
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "100");
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> 100);

        CompletableFuture<Void> future4 =
                future2.thenAcceptBoth(future3, (s, i) -> System.out.println(Double.parseDouble(s + i)));
        System.out.println("thenAcceptBoth:" + future4.get());

        // whenComplete:当CompletableFuture完成计算结果时对结果进行处理，或者当CompletableFuture产生异常的时候对异常进行处理
        // whenCompleteAsync:当CompletableFuture完成计算结果时对结果进行处理，或者当CompletableFuture产生异常的时候对异常进行处理。使用ForkJoinPool,
        //                   当CompletableFuture完成计算结果时对结果进行处理，或者当CompletableFuture产生异常的时候对异常进行处理。使用指定的线程池
        CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> s + "World")
                .thenApply(s -> s + "\nThis is CompletableFuture demo")
                .thenApply(String::toLowerCase)
                .whenComplete(
                        (result, throwable) -> {
                            System.out.println(result);
                        });

        CompletableFuture<Double> future5 = CompletableFuture.supplyAsync(() -> "100")
                .thenApply(s -> s + "100")
                .handle((s, t) -> s != null ? Double.parseDouble(s) : 0);
        System.out.println("handle:" + future5.get());

        // 当两个CompletableFuture都正常完成后，执行提供的fn，用它来组合另外一个CompletableFuture的结果
        CompletableFuture<String> future6 = CompletableFuture.supplyAsync(() -> "100");
        CompletableFuture<Integer> future7 = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture<Double> future8 =
                future6.thenCombine(future7, (s, i) -> Double.parseDouble(s + i));
        System.out.println("thenCombine:" + future8.get());

        // thenAccept()是只会对计算结果进行消费而不会返回任何结果的方法
        CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> s + "World")
                .thenApply(s -> s + "\nThis is Completable accept demo")
                .thenApply(String::toLowerCase)
                .thenAccept(System.out::println);
    }
}
