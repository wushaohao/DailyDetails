package threadpools.threadlocal;

/**
 * @author:wuhao
 * @description:ThreadLocal子线程获取不到父线程设置的值
 * @date:18/5/27
 */
public class ThreadLocal2 {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    //可访问到父线程设置的变量值
    private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("caicai");
        inheritableThreadLocal.set("caicai");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadLocal--threadA:" + threadLocal.get());
                System.out.println("inheritableThreadLocal--threadA"+inheritableThreadLocal.get());
            }
        });

        thread.start();        //(5)主线程输出线程变量值
        System.out.println("main:" + threadLocal.get());
        System.out.println("main:" + inheritableThreadLocal.get());
    }
}
