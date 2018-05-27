package threadpools.thread;

/**
 * @author:wuhao
 * @description:Thread线程创建方法:
 * @date:18/5/27
 */
public class ThreadDemo extends Thread {
    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     * @see #Thread(ThreadGroup, Runnable, String)
     */
    @Override
    public void run() {
        String threadName = this.getName();
        System.out.println(threadName+"--我开始运行了..");
    }

    /**
     * 1.类继承了 Thread 类，并重写了 run 方法，然后调用了线程的 start 方法启动了线程，当创建完 thread 对象后该线程并没有被启动执行
     * 2.当调用了 start 方法后才是真正启动了线程。其实当调用了 start 方法后线程并没有马上执行而是处于就绪状态，这个就绪状态是指该线程
     * 已经获取了除 CPU 资源外的其它资源，等获取 CPU 资源后才会真正处于运行状态.
     * 3.当 run 方法执行完毕，该线程就处于终止状态了。使用继承方式好处是 run 方法内获取当前线程直接使用 this 就可以，无须使用
     * Thread.currentThread() 方法，不好的地方是 Java 不支持多继承，如果继承了 Thread 类那么就不能再继承其它类，另外任务与代码没有分离，当多个线程执行一样的任务时候需要多份任务代码
     * @param args
     */
    public static void main(String[] args) {
        // 启动线程
        new ThreadDemo().start();
    }
}
