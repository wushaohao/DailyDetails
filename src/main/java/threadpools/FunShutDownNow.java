package threadpools;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author:wuhao
 * @description:线程终止方法shutdownNow()
 * @date:18/5/26
 */
public class FunShutDownNow {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 5; i++) {
            System.out.println(i);

            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("run...");
                        Thread.sleep(3000);
                        System.out.println("线程运行:" + Thread.currentThread().getName());
                        System.out.println("--");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        try {
            System.out.println("睡眠前:");
            Thread.sleep(1000);
            System.out.println("睡眠后");
            //shutdownNow：对正在执行的任务全部发出interrupt()，停止执行，对还未开始执行的任务全部取消，并且返回还没开始的任务列表
            List<Runnable> lists = pool.shutdownNow();
            System.out.println("执行shutdownNow后:");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
