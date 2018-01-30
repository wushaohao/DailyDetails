package javaDetails;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author:wuhao
 * @Description:Java synchronized 中的 while 和 notifyAll
 * if判断标记，只有一次，会导致不该运行的线程运行了。出现了数据错误的情况
 * while判断标记，解决了线程获取执行权后，是否要运行！
 * 用notify()，可能会造成无法唤醒对方的情况，仅仅唤醒本方线程，从而导致本方全都进入等待状态，两方进入等待，进而造成死锁，而notifyAll()可唤醒对方线程，从而解决问题
 *
 * @Date:17/9/30
 */
public class Synchroinze_1 {

    static class Buf {
        private final int MAX = 5;
        private final ArrayList<Integer> list = new ArrayList<>();

        synchronized void put(int v) throws InterruptedException {
            if (list.size() == MAX) {
                wait();
            }
            list.add(v);
//            notifyAll();
            notify();
        }

        synchronized int get() throws InterruptedException {
            /**
             * 假设现在有A, B两个线程来执行get 操作, 我们假设如下的步骤发生了:
             * 1. A 拿到了锁 line 0
             * 2. A 发现size==0, (line 1), 然后进入等待,并释放锁 (line 2)
             * 3. 此时B拿到了锁, line0, 发现size==0, (line 1), 然后进入等待,并释放锁 (line 2)
             * 4. 这个时候有个线程C往里面加了个数据1, 那么 notifyAll 所有的等待的线程都被唤醒了.
             * 5. AB 重新获取锁, 假设 又是A拿到了. 然后 他就走到line 3, 移除了一个数据, (line4) 没有问题.
             * 6. A 移除数据后 想通知别人, 此时list的大小有了变化, 于是调用了notifyAll (line5), 这个时候就把B给唤醒了, 那么B接着往下走.
             * 7. 这时候B就出问题了, 因为 其实 此时的竞态条件已经不满足了 (size==0). B以为还可以删除就尝试去删除, 结果就抛了异常了
             */

//            // line 0
//            if (list.size() == 0) {  // line 1
//                wait();  // line2
//                // line 3
//            }
            while(list.size() == 0){
                wait();
            }
            int v = list.remove(0);  // line 4
//            notifyAll(); // line 5
            notify();
            return v;
        }

        synchronized int size() {
            return list.size();
        }
    }

    public static void main(String[] args) {
        final Buf buf = new Buf();
        ExecutorService es = Executors.newFixedThreadPool(11);
        ScheduledExecutorService printer = Executors.newScheduledThreadPool(1);

        printer.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(buf.size());
            }
        },0,1,TimeUnit.SECONDS);


        es.execute(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        buf.put(1);
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        for (int i = 0; i < 10; i++) {
            es.execute(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            buf.get();
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        try {
            es.shutdown();
            es.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
