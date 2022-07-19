package fj;

import java.util.concurrent.*;

/**
 * @author:wuhao
 * @Description:fork/join测试类
 * @Date:17/12/29
 */
public class CountTask extends RecursiveTask {
    /**
     * 阀值
     * 子任务执行的分割数
     */
    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    private CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * The main computation performed by this task.
     */
    @Override
    protected Object compute() {
        int sum = 0;

        boolean isCanComputer = (end - start) <= THRESHOLD;

        if (isCanComputer) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阀值,就分裂成2个子任务计算
            int middle = (start + end) / 2;
            CountTask firstTask = new CountTask(start, middle);
            CountTask secondTask = new CountTask(middle + 1, end);
            // 执行子任务
            firstTask.fork();
            secondTask.fork();
            // 等待子任务执行完，并得到其结果
            int firstResult = (int) firstTask.join();
            int secondResult = (int) secondTask.join();
            // 合并子任务
            sum = firstResult + secondResult;
        }
        return sum;
    }


    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 生成一个计算任务
        CountTask task = new CountTask(1, 4);
        // 执行一个任务
        Future result = forkJoinPool.submit(task);

        try {
            System.out.println("执行结果是:" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
