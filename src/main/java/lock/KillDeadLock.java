package lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:wuhao
 * @description:中断方式获取锁
 * @date:18/9/2
 */
public class KillDeadLock implements Runnable {
    private ReentrantLock lock1 = new ReentrantLock();
    private ReentrantLock lock2 = new ReentrantLock();

    int lock;

    public KillDeadLock(int lock) {
        this.lock = lock;
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
        try {
            if (lock == 1) {
                // 以可以响应中断的方式加锁(可中断获取锁，与lock()不同之处在于可响应中断操作，即在获取锁的过程中可中断，注意synchronized在获取锁时是不可中断的)
                lock1.lockInterruptibly();
                Thread.sleep(500);
                lock2.lockInterruptibly();
                System.out.println("获取到2的锁..");

            } else {
                // 以可以响应中断的方式加锁
                lock2.lockInterruptibly();
                Thread.sleep(500);
                lock1.lockInterruptibly();
                System.out.println("获取到1的锁..");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 判断该锁是否被当前线程持有
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
                System.out.println("1释放锁...");
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
                System.out.println("2释放锁...");
            }
            System.err.println(Thread.currentThread().getId() + "退出！");
        }
    }

    public static void main(String[] args) {
        KillDeadLock deadLock1 = new KillDeadLock(1);
        KillDeadLock deadLock2 = new KillDeadLock(2);

        Thread t1 = new Thread(deadLock1);
        Thread t2 = new Thread(deadLock2);

        t1.start();
        t2.start();
        try {
            Thread.sleep(1000);
            //t1、t2线程开始运行时，会分别持有lock1和lock2而请求lock2和lock1，这样就发生了死锁。但是，在t2.interrupt()处给t2线程状态标记为中断后，
            // 持有重入锁lock2的线程t2会响应中断，并不再继续等待lock1，同时释放了其原本持有的lock2，这样t1获取到了lock2，正常执行完成。t2也会退出，但只是释放了资源并没有完成工作
            t2.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
