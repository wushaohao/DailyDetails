package lock.spin;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author:wuhao
 * @description:自旋锁
 * @date:18/12/10
 */
public class SpinLock {
    private AtomicReference<Thread> cas = new AtomicReference<>();

    /**
     * lock()方法利用CAS,当第一个线程A获取锁的时候,能够成功获取到,不会进入while循环,如果此时线程A没有释放锁
     * 另一个线程B又来获取锁,此时由于不满足CAS,所以就会进入while循环,不断判断是否满足CAS,直到A线程调用unlock方法释放了该锁。
     */
    public void lock() {
        Thread current = Thread.currentThread();
        // 利用CAS 但是当同一个线程再次进入时就会出线死锁
        // A线程第一次获取了锁 没有问题,当第二次获取锁的时候 CAS进行判断进入while死循环 所以在使用可重入锁解决自旋锁的死循环问题必须用count来计数并退出
        while (!cas.compareAndSet(null, current)) {

        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        cas.compareAndSet(current, null);
    }
}
