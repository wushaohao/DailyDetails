package Netty.future;

import java.util.concurrent.*;

/**
 * @author:wuhao
 * @description:
 * @date:18/7/12
 */
public class FutureTask {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        /**
         * 无返回值的Runnable
         */

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("I am task1");
            }
        };


        // 有返回值的Callable
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "task2";
            }
        };

        Future<?> future = executorService.submit(runnable1);
        Future<String> future1 = executorService.submit(callable);

        System.out.println("task1 is completed?" + future.isDone());
        System.out.println("task2 is completed?" + future1.isDone());

        while (future.isDone()) {
            System.out.println("task1 is completed!");
            break;
        }

        while (future1.isDone()) {
            try {
                System.out.println("return task2 value is:" + future1.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            break;
        }

    }
}
