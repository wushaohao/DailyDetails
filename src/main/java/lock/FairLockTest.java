package lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:wuhao
 * @description:公平锁
 * @date:18/9/2
 */
public class FairLockTest implements Runnable {

    /**
     * 公平锁
     */
    private static ReentrantLock lock = new ReentrantLock(true);

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
        while (true) {
            try {
                lock.lock();

                System.out.println(Thread.currentThread().getName() + "获取到锁...");
            } finally {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {
        FairLockTest test = new FairLockTest();

        Thread threadA = new Thread(test, "Thread-A");
        Thread threadB = new Thread(test, "Thread-B");

        threadA.start();
        threadB.start();


    }
}
