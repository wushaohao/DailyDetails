package thread;

/**
 * @author:wuhao
 * @description:join使用,当一个线程必须等待另一个线程执行完毕才能执行时可以使用join方法
 * @date:2020/3/1
 */
public class ThreadJoin_1 {
    public static void main(String[] args) {
        //
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("产品经理规划新需求!");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread1.join();
                    System.out.println("开发人员开发新需求!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread2.join();
                    System.out.println("测试人员测试新功能!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("早上:");
        System.out.println("测试人员来上班了");
        thread3.start();

        System.out.println("产品经理来上班了");
        thread1.start();

        System.out.println("开发人员来上班了");
        thread2.start();
    }
}
