package multithreading;

import java.util.concurrent.CountDownLatch;

/**
 * @author:wuhao
 * @description:CountDownLatch测试类,CountDownLatch(闭锁)是一个很有用的工具类，利用它我们可以拦截一个或多个线程使其在某个条件成熟后再执行
 * @date:18/5/19
 */
public class Players extends Thread {
    private static int count = 1;
    private final int id = count++;
    private CountDownLatch latch;

    public Players(CountDownLatch latch) {
        this.latch = latch;
    }

    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     * @see #Thread(ThreadGroup, Runnable, String)
     */
    @Override
    public void run() {
        System.out.println("【玩家" + id + "】已入场");
        // 调用countDown()方法 使初始化值每次减一 直到为0 countDown方法来操作计数器的值，每调用一次countDown方法计数器都会减1，
        // 直到计数器的值减为0时就代表条件已成熟，所有因调用await方法而阻塞的线程都会被唤醒
        latch.countDown();
        super.run();
    }

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        System.out.println("牌局开始 玩家入场...");
        new Players(countDownLatch).start();
        new Players(countDownLatch).start();
        new Players(countDownLatch).start();
        // 调用await()方法 使用interrupt形式阻塞已经准备好的线程 线程被挂起 直到countDown变为0 才继续执行
        countDownLatch.await();
        System.out.println("玩家全部到齐 开始比赛..");
    }
}
