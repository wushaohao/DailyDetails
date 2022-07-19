package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:wuhao
 * @description:生产者消费者游戏
 * @date:18/9/2
 */
public class ProducerConsumeGameLock {
    public static void main(String[] args) {
        Bucket bucket = new Bucket();
        Thread consumer = new Thread(new Consumer(bucket), "Consumer");
        Thread producer = new Thread(new Producer(bucket), "Producer");

        producer.start();
        consumer.start();

    }

}

class Consumer implements Runnable {
    private Bucket bucket;

    public Consumer(Bucket bucket) {
        this.bucket = bucket;
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
        for (int i = 0; i < 10; i++) {
            bucket.get();
        }
    }
}

class Producer implements Runnable {
    private Bucket bucket;

    public Producer(Bucket bucket) {
        this.bucket = bucket;
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
        // 来回十次交易
        for (int i = 0; i < 10; i++) {
            bucket.put((int) (Math.random() * 1000));
        }
    }
}

class Bucket {
    private int packOdBalls;
    private boolean available = false;

    private final ReentrantLock lock = new ReentrantLock();
    private Condition noBull = lock.newCondition();
    private Condition fullBull = lock.newCondition();

    /**
     * 消费者从篮子里面取出球
     */
    public int get() {
        try {
            lock.lock();
            // 如果没有就等着,但是为什么不是if呢？后面会做解析
            while (available == false) {
                System.out.println("消费者:暂时没有球可以消费我就等着...");
                try {
                    noBull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("消费者获得了" + packOdBalls + "个球");
            available = false;
            System.out.println("持有消费线程数:" + lock.getHoldCount());
            fullBull.signal();
        } finally {
            lock.unlock();
        }
        // 有的话就取出来
        return packOdBalls;
    }

    /**
     * 生产者将生产球并放入到篮子里面
     */

    public void put(int packOdBalls) {
        lock.lock();

        try {
            while (available) {
                System.out.println("生产者：既然篮子里面已经有球了我就消费完了再生产吧！");
                try {
                    fullBull.await(); // wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.packOdBalls = packOdBalls;
            available = true;
            System.out.println("生产者放进去了" + packOdBalls + "个球");
            noBull.signal(); // notify();
        } finally {
            lock.unlock();
        }
    }

}
