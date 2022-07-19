package completable.exceptions;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

/**
 * @author:wuhao
 * @description:allOf使用
 * @date:2019/12/14
 */
public class CompletableFutureAllOf {
    /**
     * 我们可以将对各future实例添加到allOf方法中，然后通过future的get（）方法获取future的状态。如果allOf里面的所有线程为执行完毕，主线程会阻塞，
     * 直到allOf里面的所有线程都执行，线程就会被唤醒。 异步编程，不想阻塞线程的话，可以使用thenAccept、thenApply、thenRun，future结束后，执行异步方法
     *
     * @param args
     */
    public static void main(String[] args) {
        //
        CompletableFuture<String> future1 =
                CompletableFuture.supplyAsync(
                        () -> {
                            try {
                                TimeUnit.SECONDS.sleep(3);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return "future1";
                        });

        future1.whenCompleteAsync(
                new BiConsumer<String, Throwable>() {
                    @Override
                    public void accept(String s, Throwable throwable) {
                        System.out.println(System.currentTimeMillis() + "" + s);
                    }
                });

        CompletableFuture<String> future2 =
                CompletableFuture.supplyAsync(
                        () -> {
                            try {
                                TimeUnit.SECONDS.sleep(2);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return "future2";
                        });
        future2.whenCompleteAsync(
                new BiConsumer<String, Throwable>() {
                    @Override
                    public void accept(String s, Throwable throwable) {
                        System.out.println(System.currentTimeMillis() + "" + s);
                    }
                });

        CompletableFuture<String> future3 =
                CompletableFuture.supplyAsync(
                        () -> {
                            try {
                                TimeUnit.SECONDS.sleep(2);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return "future2";
                        })
                        .whenCompleteAsync(
                                (s, e) -> {
                                    System.out.println("s:" + s + "\t e:" + e);
                                });

        CompletableFuture<Void> await = CompletableFuture.allOf(future1, future2, future3);
        System.out.println(Thread.currentThread().getName() + "\t is waiting");
        await.join();
        System.out.println(Thread.currentThread().getName() + "\t is end waiting");
        System.out.println("运行结束!");
    }
}
