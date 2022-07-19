package threadpools.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:wuhao
 * @description:3个间隔打印(ReentrantLock实现交通灯信号)
 * @date:18/9/1
 */
public class IntervalPrint2 implements Runnable {

    /**
     * 线程编号,Thread Number
     */
    private int tNum = 1;

    private ReentrantLock lock = new ReentrantLock();

    private Condition redCon = lock.newCondition();
    private Condition greenCon = lock.newCondition();
    private Condition yellowCon = lock.newCondition();

    public static void main(String[] args) {
        new Thread(new IntervalPrint2()).start();
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        new Thread(new RedLight(), "red light").start();
        new Thread(new GreenLight(), "green light").start();
        new Thread(new YellowLight(), "yellow light").start();
    }

    class RedLight implements Runnable {
        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            while (true) {
                lock.lock();

                while (tNum != 1) {
                    try {
                        redCon.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(Thread.currentThread().getName() + " is flashing...");
                // 停留时间，便于从控制台观看
                try {
                    TimeUnit.SECONDS.sleep(1);
                    tNum = 2;
                    greenCon.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    class GreenLight implements Runnable {
        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            while (true) {
                lock.lock();

                while (tNum != 2) {
                    try {
                        greenCon.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(Thread.currentThread().getName() + " is flashing...");
                // 停留时间，便于从控制台观看
                try {
                    TimeUnit.SECONDS.sleep(1);
                    tNum = 3;
                    yellowCon.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    class YellowLight implements Runnable {
        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            while (true) {
                lock.lock();

                while (tNum != 3) {
                    try {
                        yellowCon.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(Thread.currentThread().getName() + " is flashing...");
                // 停留时间，便于从控制台观看
                try {
                    TimeUnit.SECONDS.sleep(1);
                    tNum = 1;
                    redCon.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
