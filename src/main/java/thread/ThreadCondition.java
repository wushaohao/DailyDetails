package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:wuhao
 * @description:Condition
 * @date:2020/3/1
 */
public class ThreadCondition {
    private static Lock lock = new ReentrantLock();

    private static Condition condition1 = lock.newCondition();
    private static Condition condition2 = lock.newCondition();
    /**
     * 为什么要加这两个标识状态?
     * 如果没有状态标识，当t1已经运行完了t2才运行，t2在等待t1唤醒导致t2永远处于等待状态
     */
    private static boolean t1Run = false;
    private static boolean t2Run = false;

    public static void main(String[] args) {
        //
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                System.out.println("产品经理规划需求");
                t1Run = true;
                condition1.signal();
                lock.unlock();
            }
        });

        Thread thread2 =
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                lock.lock();

                                if (!t1Run) {
                                    System.out.println("开发人员先休息会...");
                                    try {
                                        // 当前线程等待 释放锁
                                        condition1.await();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                System.out.println("开发人员开发新需求功能");
                                t2Run = true;
                                condition2.signal();

                                lock.unlock();
                            }
                        });

        Thread thread3 =
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                lock.lock();
                                try {
                                    if (!t2Run) {
                                        System.out.println("测试人员先休息会...");
                                        condition2.await();
                                    }
                                    System.out.println("测试人员测试新功能");
                                    lock.unlock();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

        System.out.println("早上：");
        System.out.println("测试人员来上班了...");
        thread3.start();
        System.out.println("产品经理来上班了...");
        thread1.start();
        System.out.println("开发人员来上班了...");
        thread2.start();
    }
}
