package ThreadPool.threadProgram;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by wuhao on 17/4/16.
 */
public class ExecutorService_01 {
    public static void main(String[] args) {
        Set<Callable<String>> sets=new HashSet<>();

        sets.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("i am A..");
                return "A";
            }
        });

        sets.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("i am B..");
                return "B";
            }
        });

        sets.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("i am C..");
                return "C";
            }
        });

        ExecutorService executorService= Executors.newSingleThreadExecutor();

        try {
            /**
             * invokeAny(Collection) 只会返回其中一个子任务结果
             */
//            String results=executorService.invokeAny(sets);
//            System.out.println("运行结果是:"+results);


            /**
             * invokeAll(Collection) 会返回一个类型是Future<> 的List集合
             */
            List<Future<String>> lists=executorService.invokeAll(sets);
            System.out.println(""+lists.size());
            for (Future future:lists) {
                try {
                    System.out.println("执行的结果是:"+future.get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    }
}
