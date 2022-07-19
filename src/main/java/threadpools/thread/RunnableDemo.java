package threadpools.thread;

/**
 * @author:wuhao
 * @description:实现Runnable创建线程
 * @date:18/5/27
 */
public class RunnableDemo implements Runnable {

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "--Runnable 运行..");
    }

    public static void main(String[] args) {
        new Thread(new RunnableDemo()).start();
    }
}
