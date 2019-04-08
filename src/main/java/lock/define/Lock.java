package lock.define;

/**
 * @author:wuhao
 * @description:自定义不可重入锁
 * @date:2019/4/4
 */
public class Lock {
    private boolean isLocked = false;
    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    public synchronized void unLock() {
        isLocked = false;
        notify();
    }
}

class Count {
    Lock lock = new Lock();
    public void print() {
        try {
            lock.lock();
            doAdd();
            lock.unLock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doAdd() throws InterruptedException {
        lock.lock();
        System.out.println("获取到锁....");
        lock.unLock();
        System.out.println("释放锁....");
    }
}
