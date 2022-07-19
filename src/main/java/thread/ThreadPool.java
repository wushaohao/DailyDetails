package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author:wuhao
 * @description:线程池来执行线程顺序
 * @date:2020/3/1
 */
public class ThreadPool {

    /**
     * 单线程化线程池(newSingleThreadExecutor); 可控最大并发数线程池(newFixedThreadPool);
     * 可回收缓存线程池(newCachedThreadPool); 支持定时与周期性任务的线程池(newScheduledThreadPool)。
     * <p>
     * 单线程化线程池(newSingleThreadExecutor): 优点，串行执行所有任务。串行执行所有任务。如果这个唯一的线程因为异常结束，
     * 那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行
     * submit()： 提交任务。 shutdown()： 方法用来关闭线程池，拒绝新任务。
     */
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        Thread thread1 =
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("产品经理规划产品需求");
                            }
                        });

        Thread thread2 =
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("开发人员开发");
                            }
                        });

        Thread thread3 =
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("测试人员测试");
                            }
                        });

        System.out.println("早上：");
        System.out.println("产品经理来上班了");
        System.out.println("测试人员来上班了");
        System.out.println("开发人员来上班了");
        System.out.println("领导吩咐:");
        System.out.println("首先，产品经理规划新需求...");
        executorService.submit(thread1);
        System.out.println("然后，开发人员开发新需求功能...");
        executorService.submit(thread2);
        System.out.println("最后，测试人员测试新功能...");
        executorService.submit(thread3);
        executorService.shutdown();
    }
}
