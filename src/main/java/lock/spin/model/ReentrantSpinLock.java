package lock.spin.model;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author:wuhao
 * @description:可重入锁
 * @date:2020/3/5
 */
public class ReentrantSpinLock {
    private volatile AtomicReference<Thread> cas = new AtomicReference<>();

    private volatile int count;

    public void lock() {
        Thread current = Thread.currentThread();
        if (current == cas.get()) {
            count++;
            return;
        }
        while (cas.compareAndSet(null, current)) {
            // do nothing
        }
    }

    public void unlock() {
        Thread cur = Thread.currentThread();
        // 只有获得了锁才进行unlock
        if (cur == cas.get()) {
            if (count > 0) {
                count--;
            } else {
                cas.compareAndSet(cur, null);
            }
        }
    }
}

