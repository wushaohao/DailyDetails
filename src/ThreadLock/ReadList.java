package ThreadLock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhao on 17/5/7.
 * wait() 会立即释放锁，并进入等待状态，等到相应的notify并重新获得锁过后才能继续执行；
 * nitify()不会立刻释放锁，必须等待notify所在的线程执行完Synchronized块中所有的代码全部执行完成后才会释放
 *
 */
public class ReadList implements Runnable{

    private List list;


    public ReadList(List lists){
        this.list=lists;
    }

    @Override
    public void run() {
        System.out.println("ReadList is start at "+" "+System.currentTimeMillis());
        synchronized (list){
            try {
                Thread.sleep(1000);
                System.out.println("ReadList begin wait at "+" "+System.currentTimeMillis());
                list.wait();
                System.out.println("ReadList end  wait at "+" "+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("ReadList end at "+System.currentTimeMillis());

    }


    public static void main(String[] args) {
        List lists=new ArrayList();

        /**
         * 由结果可知:
         * 可见读线程开始运行，开始wait过后，写线程才获得锁；写线程走出同步块而不是notify过后，读线程才wait结束，
         * 亦即获得锁。所以notify不会释放锁，wait会释放锁。值得一提的是，notifyall()会通知等待队列中的所有线程
         */
        Thread thread=new Thread(new ReadList(lists));
        Thread thread2=new Thread(new WriteList(lists));
        thread.start();
        thread2.start();
    }
}
