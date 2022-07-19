package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:wuhao
 * @description:ReentrantLock配合Conditon使用
 * @date:18/9/2
 */
public class ReentrantLockWithConditon implements Runnable {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "线程开始等待...");
        try {
            condition.await();
            System.out.println(Thread.currentThread().getName() + "等待结束,继续运行..");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("finally释放锁...");
        }
    }

    public static void main(String[] args) {
        ReentrantLockWithConditon conditonTest = new ReentrantLockWithConditon();
        Thread threadA = new Thread(conditonTest, "Thread-A");
        threadA.start();
        try {
            Thread.sleep(1000);
            System.err.println("过了1秒后...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.lock();
        //调用该方法前需要获取到创建该对象的锁lock.lock()否则会产生,因为在condition.await()会释放锁:Exception in thread "main" java.lang.IllegalMonitorStateException
        condition.signal();
        System.out.println("手动释放🔒..");
        lock.unlock();

    }
}
