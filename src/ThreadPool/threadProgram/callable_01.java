package ThreadPool.threadProgram;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by wuhao on 17/4/16.
 *
 * 程序运行结果如下：
 * before future.get()
   Task start..
   Task is finished..
   results:4950
   after future.get()
 *
 * 从运行结果可以看到主线程在调用future.get()方法时阻塞主线程，
 * 然后callable内部开始运行并返回结果，此时future.get() 获得运行结果，主线程继续运行
 */
public class callable_01 {


    public static void main(String[] args) throws Exception {
        Callable callable=new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("Task start..");
                Thread.sleep(1000);
                int results=0;
                for (int i = 0; i < 100; i++) {
                    results+=i;
                }
                System.out.println("Task is finished..");
                return results;
            }
        };


        FutureTask futureTask=new FutureTask(callable);
        new Thread(futureTask).start();

        System.out.println("before future.get()");
        System.out.println("results:"+futureTask.get());
        System.out.println("after future.get()");
    }
}
