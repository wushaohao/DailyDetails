package ThreadLock.ReadWriteLock;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wuhao on 17/5/7.
 */
public class ReadWriteLockTest {

    public static void main(String[] args) {
        List list = new LinkedList();

        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }

        final RWLockList rwlockList = new RWLockList(list);

        Thread write = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    rwlockList.put(i);
                }
            }
        });

        Thread reader1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    rwlockList.get(i);
                }
            }
        });

        Thread reader2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    rwlockList.get(i);
                }
            }
        });

        long begin = System.currentTimeMillis();

        write.start();
        reader1.start();
        reader2.start();

        try {
            write.join();
            reader1.join();
            reader2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("RWLockList take " + (System.currentTimeMillis() - begin) + " ms");
    }
}
