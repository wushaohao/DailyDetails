package threadpools.thread;

import javafx.concurrent.Task;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author:wuhao
 * @description:有返回值的线程线程Demo
 * @date:18/5/27
 */
public class FutureTaskDemo implements Callable<String>{
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        System.out.println("我是有返回值的..");
        return "Hello FutureTask";
    }


    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<String>(new FutureTaskDemo());
        new Thread(futureTask).start();

        try {
            String result = futureTask.get();
            System.out.println("运行结果是:"+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
