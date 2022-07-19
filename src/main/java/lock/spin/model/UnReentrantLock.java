package lock.spin.model;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author:wuhao
 * @description:自旋锁经典实现
 * @date:2020/3/5
 */
public class UnReentrantLock {
    private volatile AtomicReference<Thread> owner = new AtomicReference<>();

    public void lock() {
        Thread current = Thread.currentThread();
        // 经典的自选获取
        for (; ; ) {
            if (owner.compareAndSet(null, current)) {
                return;
            }
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        // 只会尝试一次
        owner.compareAndSet(current, null);
    }
}


