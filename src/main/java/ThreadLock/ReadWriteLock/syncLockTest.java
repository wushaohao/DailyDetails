package ThreadLock.ReadWriteLock;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wuhao on 17/5/7.
 */
public class syncLockTest {

    public static void main(String[] args) {
        final List lists = new LinkedList();

        for (int i = 0; i < 10000; i++) {
            lists.add(i);
        }

        final syncList synclist = new syncList(lists);// 初始化同步锁数据

        Thread writeS = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    synclist.put(i);
                }

            }
        });


        Thread readS1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    synclist.get(i);
                }
            }
        });

        Thread readS2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    synclist.get(i);
                }
            }
        });


        long begin1 = System.currentTimeMillis();

        writeS.start();
        readS1.start();
        readS2.start();

        try {
            writeS.join();
            readS1.join();
            readS2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("syncList take " + (System.currentTimeMillis() - begin1) + " ms");


    }
}
