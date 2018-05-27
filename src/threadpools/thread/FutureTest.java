package threadpools.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author:wuhao
 * @description:FutureTask练习
 * @date:18/5/27
 */
public class FutureTest {

    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyRunnable());
         // 启动线程
        new Thread(futureTask).start();
        new Thread(futureTask).start();
        try {
            int a = futureTask.get();
            System.out.println("获取的值是:"+a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    static class MyRunnable implements Callable<Integer> {

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Integer call() throws Exception {
            return 2;
        }
    }

}
