package lock.define;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:wuhao
 * @description:ReentrantLock可重入测试
 * @date:2019/4/4
 */
public class ReentrantLockTestRe implements Runnable{
    ReentrantLock lock = new ReentrantLock();

    public void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        set();
        lock.unlock();
    }

    public void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        lock.unlock();
    }
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
        get();
    }

    public static void main(String[] args) {
        ReentrantLockTestRe testRe = new ReentrantLockTestRe();
        new Thread(testRe).start();
        new Thread(testRe).start();
        new Thread(testRe).start();
    }
}
