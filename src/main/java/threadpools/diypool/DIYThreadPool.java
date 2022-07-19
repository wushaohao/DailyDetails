package threadpools.diypool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author:wuhao
 * @description:自定义线程池
 * @date:18/5/26
 */
public class DIYThreadPool {
    private static ExecutorService pool = newFixedThreadPool(5);

    /**
     * 自定义线程池
     */
    private static ExecutorService newFixedThreadPool(int nThread) {
        return new ThreadPoolExecutor(nThread, nThread,
                0L, TimeUnit.MILLISECONDS,
                //默认的newFixedThreadPool里的LinkedBlockingQueue是一个无边界队列，如果不断的往里加任务，最终会导致内存的不可控
                new ArrayBlockingQueue<Runnable>(10000),
                new DefaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 执行
     */
    public static void execute(Runnable command) {
        pool.execute(command);
    }

    /**
     * 关闭线程池
     */
    public static void shutDown() {
        pool.shutdown();
    }

    /**
     * 自定义默认线程工厂类
     */
    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNum = new AtomicInteger(1);
        private final ThreadGroup group;
        private static final AtomicInteger threadNum = new AtomicInteger(1);
        private final String namePrefix;


        public DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "WH-pool-" + poolNum.getAndIncrement() + "-thread-";
        }

        /**
         * Constructs a new {@code Thread}.  Implementations may also initialize
         * priority, name, daemon status, {@code ThreadGroup}, etc.
         *
         * @param r a runnable to be executed by new thread instance
         * @return constructed thread, or {@code null} if the request to
         * create a thread is rejected
         */
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNum.getAndIncrement());
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

}
