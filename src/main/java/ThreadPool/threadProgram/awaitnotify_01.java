package ThreadPool.threadProgram;

/**
 * Created by wuhao on 17/4/16.
 * 通过共享对象锁lock 来使A线程打印出一个数字后 B打完所有的数字 A接着打剩下的数字
 * wait(): 该方法使获取到的对象等待
 * notify():唤醒等待的线程也就是使用wait()方法的那个线程
 */
public class awaitnotify_01 {
    public static void main(String[] args) {
        final Object lock=new Object();

        Thread A=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is A...");
                synchronized (lock){
                    System.out.println("A 获取lock锁...");
//                    for (int i = 0; i < 3; i++) {
//                        System.out.println("A:"+i);
//                        try {
//                            lock.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
                    System.out.println("A:"+0);
                    try {
                        lock.wait();
                        System.out.println("A:"+1);
                        System.out.println("A:"+2);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }

            }
        });

        Thread B=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is B...");
                synchronized (lock){
                    System.out.println("B获取到lock锁...");
                    for (int i = 0; i < 3; i++) {
                        System.out.println("B:"+i);
                    }
                    lock.notify();
                }
            }
        });

        A.start();
        B.start();
    }
}
