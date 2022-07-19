package interviews.alibaba;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:wuhao
 * @description:线程循环打印AB
 * @date:2020/6/5
 */
public class TwoThreadPrintAB {

    private int start = 1;

    /**
     * 对 flag 的写入虽然加锁保证了线程安全，但读取的时候由于 不是 volatile 所以可能会读取到旧值
     */
    private volatile boolean flag = false;

    /**
     * 重入锁,ReentrantLock
     */
    private final static Lock lock = new ReentrantLock();

    private final static int NUM = 100;

    public static void main(String[] args) {
        TwoThreadPrintAB twoThread = new TwoThreadPrintAB();
        // 基数线程
        Thread t1 = new Thread(new JiShuNum(twoThread));
        t1.setName("threadA");
        // 偶数线程
        Thread t2 = new Thread(new OuShuNum(twoThread));
        t2.setName("threadB");

        t1.start();
        t2.start();
    }

    /**
     * 奇数线程
     */
    public static class JiShuNum implements Runnable {

        private TwoThreadPrintAB number;

        public JiShuNum(TwoThreadPrintAB number) {
            this.number = number;
        }

        @Override
        public void run() {
            while (number.start <= NUM) {
                if (!number.flag) {
                    lock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + ":" + number.start);
                        number.start++;
                        number.flag = true;
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

    }

    /**
     * 偶数线程
     */
    public static class OuShuNum implements Runnable {

        private TwoThreadPrintAB number;

        public OuShuNum(TwoThreadPrintAB number) {
            this.number = number;
        }

        @Override
        public void run() {
            while (number.start <= NUM) {
                if (number.flag) {
                    lock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + ":" + number.start);
                        number.start++;
                        number.flag = false;
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
    }
}