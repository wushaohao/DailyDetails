package ThreadLock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhao on 17/5/7.
 */
public class WriteList implements Runnable{

    private List list;

    public WriteList(List lists){
        this.list=lists;
    }

    @Override
    public void run() {
        System.out.println("WriteList is start at "+System.currentTimeMillis());

        synchronized (list){
            System.out.println("WriteList get lock at "+System.currentTimeMillis());

            list.notify();
            System.out.println("WriteList list.notify() wait at "+System.currentTimeMillis());

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("WriteList get out of block at "+System.currentTimeMillis());

        }

        System.out.println("WriteList is end at "+System.currentTimeMillis());

    }
}
