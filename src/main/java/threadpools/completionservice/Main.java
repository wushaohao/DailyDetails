package threadpools.completionservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author:wuhao
 * @description:CompletionService多任务兵法执行管理器 1.ExecutorCompletionService是如何执行任务, 又是如何将任务的结果存储到完成队列中的呢?
 * 2.ExecutorCompletionService在submit任务时, 会创建一个QueueingFuture, 然后将创建的QueueingFuture丢给executor, 让executor完成任务的执行工作
 * QueueingFuture继承与FutureTask类, 而FutureTask实现了两个接口Runnable和Future.
 * Runnable一般表示要执行的任务的过程, 而Future则表述执行任务的 结果 (或者说是任务的一个句柄, 可获取结果, 取消任务等).
 * 因此FutureTask就是一个有结果可期待的任务. FutureTask实现了run方法, 我们指定此方法一般是在在工作线程(不是submit线程) 执行的
 * @date:18/9/13
 */
public class Main {
    private final static int N = 50;

    private static Random random = new Random();

    public static void main(String[] args) {

    }

    /**
     * 1.用List收集任务结果 (List记录每个submit返回的Future)
     * 2.循环查看结果, Future不一定完成, 如果没有完成, 那么调用get会租塞
     * 3.如果排在前面的任务没有完成, 那么就会阻塞, 这样后面已经完成的任务就没法获得结果了, 导致了不必要的等待时间
     * 更为严重的是: 第一个任务如果几个小时或永远完成不了, 而后面的任务几秒钟就完成了, 那么后面的任务的结果都将得不到处理
     * 导致: 已完成的任务可能得不到及时处理
     */
    private static void case1() {
        ThreadPoolExecutor pools = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10));

        List<Future<String>> taskResults = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            taskResults.add(pools.submit(new Callable<String>() {
                /**
                 * Computes a result, or throws an exception if unable to do so.
                 *
                 * @return computed result
                 * @throws Exception if unable to compute a result
                 */
                @Override
                public String call() throws Exception {
                    Thread.sleep(random.nextInt(5000));
                    return Thread.currentThread().getName();
                }
            }));
        }

        //处理任务结果
        int count = 0;
        System.out.println("handle result start....");
        for (Future<String> f : taskResults
        ) {
            try {
                System.out.println(f.get());
                count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("handle result end");
        System.out.println(count + " task done !");

        // 使用完线程池一定得记得关闭
        pools.shutdown();

    }

    /**
     * 1. 查看任务是否完成, 如果完成, 就获取任务的结果, 然后从任务列表中删除任务.
     * 2. 如果任务未完成, 就跳过此任务, 继续查看下一个任务结果.
     * 3. 如果到了任务列表末端, 那么就从新回到任务列表开始, 然后继续从第一步开始执行
     */
    private static void case2() {
        ThreadPoolExecutor pools = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10));
        List<Future<String>> results = new ArrayList<>();

        for (int i = 0; i < N; i++) {

            Callable<String> task = new Callable<String>() {
                /**
                 * Computes a result, or throws an exception if unable to do so.
                 *
                 * @return computed result
                 * @throws Exception if unable to compute a result
                 */
                @Override
                public String call() throws Exception {
                    Thread.sleep(random.nextInt(5000));
                    return Thread.currentThread().getName();
                }
            };

            results.add(pools.submit(task));

        }

        int count = 0;
        //自旋, 获取结果
        System.out.println("handle result begin");
        for (int i = 0; i < results.size(); i++) {
            Future<String> future = results.get(i);
            if (future.isDone()) {
                try {
                    String reStr = future.get();
                    System.out.println("获取到的执行结果是:" + reStr);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                // 移除已完成的任务
                results.remove(future);
                i--;
                // 完成任务计数器 +1
                count++;
            }

            //回到列表开头, 从新获取结果
            if (i == results.size() - 1) {
                i = -1;
            }
        }

        System.out.println("handle result end");
        System.out.println(count + " task done !");

        //线程池使用完必须关闭
        pools.shutdown();
    }

    /**
     * 使用ExecutorCompletionService管理异步任务
     * 1. Java中的ExecutorCompletionService<V>本身有管理任务队列的功能
     * i. ExecutorCompletionService内部维护列一个队列, 用于管理已完成的任务
     * ii. 内部还维护列一个Executor, 可以执行任务
     * <p>
     * 2. ExecutorCompletionService内部维护了一个BlockingQueue, 只有完成的任务才被加入到队列中
     * <p>
     * 3. 任务一完成就加入到内置管理队列中, 如果队列中的数据为空时, 调用take()就会阻塞 (等待任务完成)
     * i. 关于完成任务是如何加入到完成队列中的, 请参考ExecutorCompletionService的内部类QueueingFuture的done()方法
     * <p>
     * 4. ExecutorCompletionService的take/poll方法是对BlockingQueue对应的方法的封装, 关于BlockingQueue的take/poll方法:
     * i. take()方法, 如果队列中有数据, 就返回数据, 否则就一直阻塞;
     * ii. poll()方法: 如果有值就返回, 否则返回null
     * iii. poll(long timeout, TimeUnit unit)方法: 如果有值就返回, 否则等待指定的时间; 如果时间到了如果有值, 就返回值, 否则返回null
     * <p>
     * 解决了已完成任务得不到及时处理的问题
     */
    private static void completionServiceCase() {
        ThreadPoolExecutor pools = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10));

        ExecutorCompletionService<String> executorCompletionService = new ExecutorCompletionService<String>(pools);

        for (int i = 0; i < N; i++) {
            executorCompletionService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Thread.sleep(random.nextInt(5000));
                    return Thread.currentThread().getName();
                }
            });
        }

        int completeTaskCount = 0;

        while (completeTaskCount < 50) {
            try {
                Future<String> resultHolder = executorCompletionService.take();
                System.out.println("result: " + resultHolder.get());
                completeTaskCount++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println(completeTaskCount + " task done !");

        // 一定要关闭线程池
        pools.shutdown();
    }
}
