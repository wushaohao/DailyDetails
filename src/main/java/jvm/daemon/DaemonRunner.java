package jvm.daemon;


import java.util.Scanner;

/**
 * @author:wuhao
 * @description:守护线程
 * @date:18/12/14
 */
public class DaemonRunner implements Runnable {
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
            for (int i = 0; i < 100; i++) {
                System.out.println(i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread daemonThread = new Thread(new DaemonRunner());
        // 设置为守护线程
        daemonThread.setDaemon(true);
        daemonThread.start();

        System.out.println("isDaemon=" + daemonThread.isDaemon());
        Scanner scanner = new Scanner(System.in);
        //接受输入,使程序在此停顿,一旦接受用户输入,main线程结束,JVM退出
        scanner.next();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("JVM EXIT!");
            }
        }));

    }
}
