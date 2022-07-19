package thread;


/**
 * @author:wuhao
 * @description:主线程join,通过主程序join使线程按顺序执行
 * @date:2020/3/1
 */
public class MainJoin {
    public static void main(String[] args) throws InterruptedException {
        //
        Thread thread1 =
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("产品经理正在规划新需求...");
                            }
                        });

        Thread thread2 =
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("开发人员开发新需求功能");
                            }
                        });

        Thread thread3 =
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("测试人员测试新功能");
                            }
                        });

        System.out.println("早上:");
        System.out.println("产品经理来上班了");
        System.out.println("测试人员来上班了");
        System.out.println("开发人员来上班了");

        thread1.start();
        //在父进程调用子进程的join()方法后，父进程需要等待子进程运行完再继续运行。
        System.out.println("开发人员和测试人员休息会...");
        thread1.join();
        System.out.println("产品经理新需求规划完成!");
        thread2.start();
        System.out.println("测试人员休息会...");
        thread2.join();
        thread3.start();
    }
}
