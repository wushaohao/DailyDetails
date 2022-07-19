package threadpools.threadlocal;

/**
 * @author:wuhao
 * @description:ThreadLocal测试类
 * @date:18/5/27
 */
public class ThreadLocal1 {
    /**
     * ThreadLocal 是在 JDK 包里面提供的，它提供了线程本地变量，也就是如果你创建了一个 ThreadLocal 变量，
     * 那么访问这个变量的每个线程都会有这个变量的一个本地拷贝，多个线程操作这个变量的时候，
     * 实际是操作的自己本地内存里面的变量，从而避免了线程安全问题，创建一个 ThreadLocal 变量后每个线程会拷贝一个变量到自己本地内存
     */
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void print(String str) {
        System.out.println(str + ":" + threadLocal.get());
        //threadLocal.remove();
    }

    public static void main(String[] args) {
        final Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("threadA local variable");
                print("threadA");
                System.out.println("threadA remove after" + ":" + threadLocal.get());
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("threadB local variable");
                print("threadB");
                System.out.println("threadB remove after" + ":" + threadLocal.get());
            }
        });

        threadA.start();
        threadB.start();

    }
}
