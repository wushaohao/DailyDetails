package lock.define;

/**
 * @author:wuhao
 * @description:Synchronize测试是否可重入
 * @date:2019/4/4
 */
public class SynchronizedTestRe implements Runnable{
    public synchronized void get() {
        System.out.println(Thread.currentThread().getId());
        set();
    }

    public synchronized void set() {
        System.out.println(Thread.currentThread().getId());
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
        SynchronizedTestRe test = new SynchronizedTestRe();
        new Thread(test).start();
        new Thread(test).start();
        new Thread(test).start();
    }
}
