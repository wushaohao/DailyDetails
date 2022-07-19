package threadpools.thread;

/**
 * @author:wuhao
 * @description:线程间隔输出
 * @date:18/9/1
 */
public class IntervalPrint {
    private static final Object object = new Object();

    private static boolean flag = true;

    public static void main(String[] args) {

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (object) {
                        while (!flag) {
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        flag = false;
                        System.out.println("A");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        object.notify();
                    }
                }

            }
        });


        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (object) {
                        while (flag) {
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        flag = true;
                        System.out.println("B");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        object.notify();
                    }
                }

            }
        });

        threadA.start();
        threadB.start();

    }
}
