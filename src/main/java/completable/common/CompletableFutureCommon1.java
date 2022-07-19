package completable.common;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author:wuhao
 * @description:入门简介
 * @date:2019/12/16
 */
public class CompletableFutureCommon1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // runAsync 和 supplyAsync 方法的区别是runAsync返回的CompletableFuture是没有返回值的
        CompletableFuture<Void> future =
                CompletableFuture.runAsync(
                        () -> {
                            System.out.println("Hello");
                        });

        System.out.println("runAsync:" + future.get());

        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");

        System.out.println("supplyAsync:" + future1.get());

        // future.get()在等待执行结果时，程序会一直block，如果此时调用complete(T t)会立即执行
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Hello");
        future2.complete("world");
        System.out.println("complete:" + future2.get());

        // 如果future已经执行完毕能够返回结果，此时再调用complete(T t)则会无效。
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "Hello");
        Thread.sleep(5000);
        future3.complete("world");
        System.out.println("complete is done:" + future3.get());

        // 如果使用completeExceptionally(Throwable ex)则抛出一个异常，而不是一个成功的结果。
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> "Hello");
        future4.completeExceptionally(new Exception());
        System.out.println("completeExceptionally:" + future4.get());

        //thenApply的功能相当于将CompletableFuture<T>转换成CompletableFuture<U>
        CompletableFuture<String> future5 =
                CompletableFuture.supplyAsync(() -> "Hello")
                        .thenApply(
                                s -> s + "World")
                        .thenApply(String::toUpperCase);
        System.out.println("thenApply:" + future5.get());

        CompletableFuture<Double> future6 =
                CompletableFuture.supplyAsync(() -> "100")
                        .thenApply(Integer::parseInt)
                        .thenApply(i -> i * 10.0);
        System.out.println("thenApply:" + future6.get());

        // Either 表示的是两个CompletableFuture，当其中任意一个CompletableFuture计算完成的时候就会执行
        Random random = new Random();
        CompletableFuture<String> future7 =
                CompletableFuture.supplyAsync(
                        () -> {
                            try {
                                Thread.sleep(random.nextInt(1000));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return "from future7";
                        });

        CompletableFuture<String> future8 =
                CompletableFuture.supplyAsync(
                        () -> {
                            try {
                                Thread.sleep(random.nextInt(1000));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return "from future8";
                        });

        CompletableFuture<Void> future9 =
                future7.acceptEither(future8, str -> System.out.println("the future is:" + str));
        System.out.println("acceptEither:" + future9.get());

        //allOf()方法所返回的CompletableFuture，并不能组合前面多个CompletableFuture的计算结果。于是我们借助Java 8的Stream来组合多个future的结果
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> "tony");

        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> "cafei");

        CompletableFuture<String> futureC = CompletableFuture.supplyAsync(() -> "aaron");
        CompletableFuture.allOf(futureA, futureB, futureC)
                .thenApply(
                        v ->
                                Stream.of(futureA, futureB, futureC)
                                        .map(CompletableFuture::join)
                                        .collect(Collectors.joining(" ")))
                .thenAccept(System.out::println);

        //使用anyOf()时，只要某一个future完成，就结束了。所以执行结果可能是"from future1"、"from future2"、"from future3"中的任意一个。
        //anyOf 和 acceptEither、applyToEither的区别在于，后两者只能使用在两个future中，而anyOf可以使用在多个future中
        Random rand = new Random();
        CompletableFuture<String> future10 =
                CompletableFuture.supplyAsync(
                        () -> {
                            try {
                                Thread.sleep(rand.nextInt(1000));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return "from future10";
                        });

        CompletableFuture<String> future11 =
                CompletableFuture.supplyAsync(
                        () -> {
                            try {
                                Thread.sleep(rand.nextInt(1000));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return "from future11";
                        });

        CompletableFuture<String> future12 =
                CompletableFuture.supplyAsync(
                        () -> {
                            try {
                                Thread.sleep(rand.nextInt(1000));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return "from future12";
                        });
        CompletableFuture<Object> future13 = CompletableFuture.anyOf(future10, future11, future12);
        System.out.println("anyOf:" + future13.get());

        /** 异常处理 */
        // 只有当CompletableFuture抛出异常的时候，才会触发这个exceptionally的计算，调用function计算值
        CompletableFuture.supplyAsync(() -> "hello world")
                .thenApply(
                        s -> {
                            s = null;
                            int length = s.length();
                            return length;
                        })
                .thenAccept(i -> System.out.println(i))
                .exceptionally(
                        t -> {
                            System.out.println("Unexpected error:" + t);
                            return null;
                        });

        CompletableFuture.supplyAsync(() -> "hello world")
                .thenApply(
                        s -> {
                            s = null;
                            int length = s.length();
                            return length;
                        })
                .thenAccept(i -> System.out.println(i))
                .whenComplete(
                        (result, throwable) -> {
                            if (throwable != null) {
                                System.out.println("Unexpected error:" + throwable);
                            } else {
                                System.out.println("whenComplete:" + result);
                            }
                        });
    }
}
