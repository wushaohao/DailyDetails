package threadpools.thread;

/**
 * @author:wuhao
 * @description:线程join方法
 * @date:18/5/27
 */
public class FunJoin {
    /**
     * wait()等待通知方法是属于Object类的，而join()方法则是直接在Thread类里面提供的,join()是无参,返回值为void的方法
     */
    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("child threadA start!");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("child threadA over!");
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("child threadB start!");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("child threadB over!");
            }
        });

        threadA.start();
        threadB.start();
        System.out.println("wait all child thread over!");

        try {
            //等待子线程执行完毕返回
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("all child thread over!");
    }
}
