package completable;

import java.util.concurrent.CompletableFuture;

/**
 * @author:wuhao
 * @description:CompletableFuture的exceptionlly异常处理
 * @date:2019/12/18
 */
public class CompletableFutureExceptionally {

    public static void main(String[] args) {
        //

        CompletableFuture.supplyAsync(() -> 4 / 0)
                .exceptionally(
                        e -> {
                            System.out.println("输出的异常是:" + e);
                            return 0;
                        })
                .thenApply(
                        result -> {
                            System.out.println("运行的结果是:" + result);
                            return result;
                        });

        // thenAccept()是只会对计算结果进行消费而不会返回任何结果的方法,有异常也不关心
        CompletableFuture.supplyAsync(() -> 1 / 0)
                .thenAccept(
                        (result) -> {
                            System.out.println("thenAccept:" + result);
                        });
    }


}
