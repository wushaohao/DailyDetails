package thread;


import java.util.concurrent.CountDownLatch;

/**
 * @author:wuhao
 * @description:计数器
 * @date:2020/3/1
 */
public class ThreadCountDownLatch {

    /**
     * 用于判断线程是否一直执行，倒计时设置为1 每次减一
     */
    private static CountDownLatch latch = new CountDownLatch(1);

    private static CountDownLatch latch2 = new CountDownLatch(1);

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("产品经理规划产品");
                latch.countDown();
            }
        });

        Thread thread2 =
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    latch.await();
                                    System.out.println("开发人员开发新需求");
                                    latch2.countDown();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    latch2.await();
                    System.out.println("测试人员开始测试...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("早上：");
        System.out.println("测试人员来上班了...");
        thread3.start();
        System.out.println("产品经理来上班了...");
        thread1.start();
        System.out.println("开发人员来上班了...");
        thread2.start();
    }
}
