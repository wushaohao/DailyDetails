package ThreadLock.ReadWriteLock;

import java.util.List;

/**
 * Created by wuhao on 17/5/7.
 */
public class syncList {
    private List list;

    public syncList(List lists) {
        this.list = lists;
    }

    public synchronized int get(int k) {
        return (int) list.get(k);
    }

    public synchronized void put(int value) {
        list.add(value);
    }
}
