package threadpools;

/**
 * @author:wuhao
 * @description:守护线程应用
 * @date:18/11/6
 */
public class ModifyDaemonThread implements Runnable {

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
        System.out.println("运行用户线程...");
        for (int i = 0; i < 5; i++) {
            System.out.println("Thinking In Java 更新到版本" + i);
        }
    }

    public Thread daemon() {
        class Timer implements Runnable {
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
                System.out.println("运行守护线程");
                long currentTime = System.currentTimeMillis();
                long processTime = 0;
                while (true) {
                    if (System.currentTimeMillis() - currentTime > processTime) {
                        processTime = System.currentTimeMillis() - currentTime;
                        System.out.println("线程运行时间为： " + processTime);
                    }
                }
            }
        }
        Thread daemonThread = new Thread(new Timer());
        return daemonThread;
    }


    public static void main(String[] args) {
        Thread userThread = new Thread(new ModifyDaemonThread());
        Thread daemonThread = new ModifyDaemonThread().daemon();

        // 在执行用户线程之前，一定要先设置守护线程
        daemonThread.setDaemon(true);
        userThread.start();

        daemonThread.start();
    }
}



