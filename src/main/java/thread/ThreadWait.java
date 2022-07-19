package thread;

import lombok.SneakyThrows;

/**
 * @author:wuhao
 * @description:wait
 * @date:2020/3/1
 */
public class ThreadWait {
    private static Object myLock1 = new Object();
    private static Object myLock2 = new Object();

    /**
     * 为什么要加这两个标识状态? 如果没有状态标识，当t1已经运行完了t2才运行，t2在等待t1唤醒导致t2永远处于等待状态
     */
    private static boolean t1Run = false;

    private static boolean t2Run = false;

    public static void main(String[] args) {
        //
        Thread thread1 =
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                synchronized (myLock1) {
                                    System.out.println("产品经理规划新需求");
                                    t1Run = false;
                                    myLock1.notify();
                                }
                            }
                        });

        Thread thread2 =
                new Thread(
                        new Runnable() {
                            @SneakyThrows
                            @Override
                            public void run() {
                                synchronized (myLock1) {
                                    if (!t1Run) {
                                        System.out.println("开发人员先休息会儿");
                                        myLock1.wait();
                                    }
                                    synchronized (myLock2) {
                                        System.out.println("开发人员开发新功能");
                                        myLock2.notify();
                                    }
                                }
                            }
                        });

        Thread thread3 =
                new Thread(
                        new Runnable() {
                            @SneakyThrows
                            @Override
                            public void run() {
                                synchronized (myLock2) {
                                    if (!t2Run) {
                                        System.out.println("测试人员先休息会儿");
                                        myLock2.wait();
                                    }
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
