package createthreadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

/**
 * @author:wuhao
 * @Description:手动创建线程池
 * @Date:17/11/21
 */
public class CreateThreadPool {

    /**
     * 创建线程池一
     *
     * @param count
     * @return
     */
    public static ExecutorService createThreadPool(int count) {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(count, new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
        return executorService;
    }

    /**
     * 创建线程池二
     *
     * @param count
     * @param maximumPoolSize
     * @return
     */
    public static ExecutorService createThreadPool(int count, int maximumPoolSize) {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(count, 200, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        return pool;
    }
}
