package lock.spin;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author:wuhao
 * @description:可重入自旋锁
 * @date:18/12/10
 */
public class ReentrantSpinLock {
    private AtomicReference<Thread> cas = new AtomicReference<>();
    private int count;

    public void lock() {
        Thread current = Thread.currentThread();
        if (current == cas.get()) {
            // 如果当前线程已经获得锁,计数器加一,然后返回
            count++;
            return;
        }

        // 没有获取到锁 CAS自旋
        while (cas.compareAndSet(null, current)) {
            // do nothing
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        if (current == cas.get()) {
            // 如果大于0,表示当前线程多次获取了该锁,释放锁通过count减一来模拟
            if (count > 0) {
                count--;
            } else {
                // 如果count=0,可以将锁释放,这样就能保证获取锁的次数与释放锁的次数是一致的了
                cas.compareAndSet(current, null);
            }
        }

    }
}
