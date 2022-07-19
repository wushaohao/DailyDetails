package interviews.alibaba;

public class ThreadPrintAB {

    public static Boolean flag = true;
    public static int i = 0;
    public static Object lock = new Object();

    private static final int NUM = 100;

    public static void main(String[] args) {
        Thread wait = new Thread(new ThreadPrintAB.Wait());
        Thread notify = new Thread(new ThreadPrintAB.Notify());
        wait.start();
        notify.start();
    }


    public static class Wait implements Runnable {
        @Override
        public void run() {
            while (i < NUM) {
                synchronized (lock) {
                    if (flag) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("threadB:" + "B");
                        flag = !flag;
                        i++;
                        lock.notify();
                    }
                }
            }
        }
    }


    public static class Notify implements Runnable {
        @Override
        public void run() {
            while (i < NUM) {
                synchronized (lock) {
                    if (!flag) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        flag = !flag;
                        System.out.println("threadA:" + "A");
                        i++;
                        lock.notify();
                    }
                }
            }
        }
    }
}