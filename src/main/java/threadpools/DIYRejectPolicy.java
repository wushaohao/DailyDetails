package threadpools;


import java.util.concurrent.*;

/**
 * @author:wuhao
 * @description:自定义拒绝策略:将任务插入到队列中，直到队列中有空闲并插入成功的时候，否则将根据最大等待时间一直阻塞，直到超时。
 * @date:18/5/26
 */
public class DIYRejectPolicy implements RejectedExecutionHandler {
    /**
     * 等待队列插槽的最长时间
     */
    private final long maxWait;

    public DIYRejectPolicy(long maxWait) {
        this.maxWait = maxWait;
    }

    /**
     * Method that may be invoked by a {@link ThreadPoolExecutor} when
     * {@link ThreadPoolExecutor#execute execute} cannot accept a
     * task.  This may occur when no more threads or queue slots are
     * available because their bounds would be exceeded, or upon
     * shutdown of the Executor.
     * <p>
     * <p>In the absence of other alternatives, the method may throw
     * an unchecked {@link RejectedExecutionException}, which will be
     * propagated to the caller of {@code execute}.
     *
     * @param r        the runnable task requested to be executed
     * @param executor the executor attempting to execute this task
     * @throws RejectedExecutionException if there is no remedy
     */
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if (!executor.isShutdown()) {
            BlockingQueue<Runnable> queue = executor.getQueue();
            System.out.println("Attempting to queue task execution for" + this.maxWait + "milliseconds");

            try {
                if (queue.offer(r, this.maxWait, TimeUnit.SECONDS)) {
                    throw new RejectedExecutionException("Max wait time expired to queue task");
                }
                System.out.println("Task execution queued");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RejectedExecutionException("Interrupted", e);
            }
        } else {
            throw new RejectedExecutionException("Executor has been shut down");
        }
    }
}
