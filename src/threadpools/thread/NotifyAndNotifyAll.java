package threadpools.thread;

/**
 * @author:wuhao
 * @description:唤醒方法demo
 * @date:18/5/27
 */
public class NotifyAndNotifyAll {
    private static volatile Object resourceA = new Object();

    /**
     * @param args
     */
    public static void main(String[] args) {

        final Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println("threadA get resourceA locker");
                    System.out.println("threadA start wait");
                    try {
                        resourceA.wait();
                        System.out.println("threadA end wait");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println("threadB get resourceA lock");

                    System.out.println("threadB begin wait");

                    try {
                        resourceA.wait();
                        System.out.println("threadB end wait");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {

                    System.out.println("threadC begin notify");
                    //企图希望在线程A和线程B都因调用共享资源resourceA的wait()方法而被阻塞后，线程C在调用resourceA的notify()方法,希望可以唤醒线程A和线程B,但是从执行结果看只有一个线程A被唤醒了，线程B没有被唤醒
                    resourceA.notify();
                    //可知线程A和线程B被挂起后，线程C调用notifyAll()函数会唤醒在resourceA等待的所有线程，这里线程A和线程B都会被唤醒，只是线程B先获取到resourceA上面的锁然后从wait()方法返回,等线程B执行完毕后，
                    //线程A又获取了resourceA 上面的锁，然后从 wait()方返回，当线程A执行完毕，主线程就返回后，然后打印输出
                    //resourceA.notifyAll();
                }
            }
        });

        /**
         * 如下代码开启了三个线程，其中线程A和B分别调用了共享资源resourceA的wait()方法,线程C则调用了notifyAll() 方法
         */
        threadA.start();
        threadB.start();
        try {
            //启动线程C前首先调用sleep()方法让主线程休眠1s,目的是让线程A和B全部执行到调用wait()方法后在调用线程C的notify()方法
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadC.start();
        try {
            // 等待线程结束
            threadA.join();
            threadB.join();
            threadC.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main over");
    }
}
