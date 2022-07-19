package threadlocal;

/**
 * @author:wuhao
 * @description:ThreadLocal测试类
 * @date:2019-08-08
 */
public class ThreadLocalTest {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        /**
         * 创建2个线程 线程一设置的值对线程二没有任何影响
         * 说明ThreadLocal对没给线程都是单独的不会进行数据共享 每个线程都会开辟自己的工作内存用于ThreadLocal的操作和值设置
         * 一个ThreadLocal只能存储一个Object对象，如果需要存储多个Object对象那么就需要多个ThreadLocal
         */
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                threadLocal.set(i);
                System.out.println(Thread.currentThread().getName() + "-----" + threadLocal.get());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    threadLocal.remove();
                }
            }
        }, "threadLocal1").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "-----" + threadLocal.get());

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    threadLocal.remove();
                }
            }
        }, "threadLocal2").start();

    }
}
