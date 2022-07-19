package ThreadLock.ReadWriteLock;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by wuhao on 17/5/7.
 */
public class RWLockList {
    private List list;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public RWLockList(List lists) {
        this.list = lists;
    }

    public int get(int k) {
        readLock.lock();
        try {
            return (int) list.get(k);

        } finally {
            readLock.unlock();
        }
    }

    public void put(int value) {
        writeLock.lock();
        try {
            list.add(value);
        } finally {
            writeLock.unlock();
        }
    }
}
