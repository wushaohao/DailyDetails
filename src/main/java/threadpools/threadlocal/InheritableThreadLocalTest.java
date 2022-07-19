package threadpools.threadlocal;

/**
 * @author:wuhao
 * @description:InheritableThreadLocal测试
 * @date:2020/4/2
 */
public class InheritableThreadLocalTest {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
    private static InheritableThreadLocal<Integer> integerInheritableThreadLocal = new InheritableThreadLocal<Integer>();

    public static void main(String[] args) {
        threadLocal.set(1001);
        integerInheritableThreadLocal.set(1002);

        new Thread(() -> System.out.println(Thread.currentThread().getName() + ":"
                + threadLocal.get() + "\t"
                + integerInheritableThreadLocal.get())).start();
    }
}
