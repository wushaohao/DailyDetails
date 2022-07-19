package threadpools;

import java.util.concurrent.*;

/**
 * @author:wuhao
 * @description:无界队列
 * @date:18/9/12
 */
public class InfiniteQueue {
    public static void main(String[] args) {
        /**
         * 自定义线程池
         * 队列:
         * 1) ArrayBlockingQueue ：一个由数组结构组成的有界阻塞队列。
         * 2) LinkedBlockingQueue ：一个由链表结构组成的有界阻塞队列。
         * 3) PriorityBlockingQueue ：一个支持优先级排序的无界阻塞队列。
         * 4) DelayQueue：一个使用优先级队列实现的无界阻塞队列。
         * 5) SynchronousQueue：一个不存储元素的阻塞队列。
         * 6) LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。
         * 7) LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。
         * 拒绝策略:
         * AbortPolicy：直接抛出异常。
         * CallerRunsPolicy：只用调用者所在线程来运行任务。
         * DiscardOldestPolicy：丢弃队列里最近的一个任务，并执行当前任务。
         * DiscardPolicy：不处理，丢弃掉。
         */
        final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 5, 1000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.AbortPolicy());

        for (; ; ) {
            poolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "开始发车了..");
                    System.out.println("队列的大小是:" + poolExecutor.getQueue().size());
                }
            });
        }


    }
}
