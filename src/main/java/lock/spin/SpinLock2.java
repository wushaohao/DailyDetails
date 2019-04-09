package lock.spin;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author:wuhao
 * @description:可重入锁解决自旋锁问题(可重入自旋锁)
 * @date:2019/4/8
 */
public class SpinLock2 {
    private AtomicReference<Thread> reference = new AtomicReference<>();

    private int count;
    public void lock() {
        Thread thread = Thread.currentThread();
        /**
         * 判断当前线程是否已经进入 如果已经进入计数器加一 退出lock
         */
        if (thread == reference.get()) {
            count++;
            return;
        }

        while (!reference.compareAndSet(null, thread)) {

        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        /**
         * 判断当前线程是否已经拥有锁 是 判断计数器是否为0 直至将计数器清0
         */
        if (current == reference.get()) {
            if (count != 0) {
                count--;
            } else {
                reference.compareAndSet(current, null);
            }
        }
    }
}
