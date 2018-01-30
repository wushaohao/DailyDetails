package ThreadPool.threadProgram;

/**
 * Created by wuhao on 17/4/16.
 */
public class join_01 {

    public static void commonprintNum(){
        /**
         * 线程A和线程B几乎同时执行完成
         */
        Thread A=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is A....");
                for (int i = 0; i < 3; i++) {
                    System.out.println("线程A:"+i);
                }
            }
        });

        Thread B=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is B....");
                for (int i = 0; i < 3; i++) {
                    System.out.println("线程B:"+i);
                }
            }
        });

        A.start();
        B.start();
    }


    public static void joinPrintnum(){

        final Thread A=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is A....");
                for (int i = 0; i < 3; i++) {
                    System.out.println("线程A:"+i);
                }
            }
        });

        Thread B=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is B....");
                try {
                    /**
                     * A.join()
                     * 使用join()的方法 表示等待当前线程执行完成后在执行其他线程
                     */
                    A.join();
                    for (int i = 0; i < 3; i++) {
                        System.out.println("线程B:"+i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        A.start();
        B.start();
    }

    public static void main(String[] args) {
        join_01.commonprintNum();
        join_01.joinPrintnum();
    }
}
