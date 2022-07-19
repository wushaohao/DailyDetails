package interviews.alibaba;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:wuhao
 * @description:线程循环打印AB
 * @date:2020/6/5
 */
public class PrintAB {
    ReentrantLock lock = new ReentrantLock();
    Condition a = lock.newCondition();
    Condition b = lock.newCondition();

    private static final int NUM = 100;

    public static void main(String[] args) {
        PrintAB p = new PrintAB();
        A a = p.new A();
        B b = p.new B();
        a.setName("threadA");
        b.setName("threadB");
        a.start();
        b.start();
    }

    class A extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "A");
                    b.signal();
                    a.await();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public class B extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "B");
                    a.signal();
                    b.await();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
