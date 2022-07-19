package completable;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author:wuhao
 * @description:CompletableFuture测试demo
 * @date:2019/12/12
 */
public class CompletableFutureDemo {
    ExecutorService executorService = Executors.newFixedThreadPool(3);

    private static boolean submit(String order) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("抛一个异常" + order);
    }

    @Test
    public void test() {
        //
        String[] orders = {"1", "2", "3", "4", "5", "6"};
        Arrays.stream(orders)
                .forEach(
                        id ->
                                CompletableFuture.supplyAsync(() -> submit(id), executorService)
                                        .exceptionally(
                                                e -> {
                                                    System.out.println(e);
                                                    return false;
                                                }));

        executorService.shutdown();

        while (executorService.isTerminated()) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


}
