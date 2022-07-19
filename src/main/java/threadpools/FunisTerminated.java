package threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author:wuhao
 * @description:
 * @date:18/5/26
 */
public class FunisTerminated {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 5; i++) {
            System.err.println(i);
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("start run");
                        Thread.sleep(3000);
                        System.out.println("-----当前线程是:" + Thread.currentThread().getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        try {
            System.out.println("睡1s前:");
            Thread.sleep(1000);
            System.out.println("线程池关闭前");
            pool.shutdown();
            System.out.println("线程池关闭后:");
            while (true) {
                //还有一些业务场景下需要知道线程池中的任务是否全部执行完成，当我们关闭线程池之后，
                // 可以用isTerminated来判断所有的线程是否执行完成，千万不要用isShutdown，isShutdown只是返回你是否调用过shutdown的结果
                if (pool.isTerminated()) {
                    System.out.println("所有的子线程结束了!");
                    break;
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
