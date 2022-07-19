package lock.spin;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author:wuhao
 * @description:自旋锁变种
 * @date:18/12/10
 */
public class TicketLock {
    /**
     * 服务号
     */
    private AtomicInteger serviceNum = new AtomicInteger();

    /**
     * 排队号
     */
    private AtomicInteger ticketNum = new AtomicInteger();

    /**
     * lock:获取锁，如果获取成功，返回当前线程的排队号，获取排队号用于释放锁.
     *
     * @return
     */
    public int lock() {
        int currentTicketNum = ticketNum.incrementAndGet();
        while (currentTicketNum != serviceNum.get()) {
            // do nothing
        }
        return currentTicketNum;
    }

    /**
     * unlock:释放锁，传入当前持有锁的线程的排队号
     */
    public void unlock() {
        int num = ticketNum.get();
        serviceNum.compareAndSet(num, num + 1);

    }

    /**
     * 线程获取锁之后，将它的排队号返回，等该线程释放锁的时候，需要将该排队号传入。但这样是有风险的，因为这个排队号是可以被修改的，
     * 一旦排队号被不小心修改了，那么锁将不能被正确释放
     */
}
