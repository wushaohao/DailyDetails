package threadpools.thread;

/**
 * @author:wuhao
 * @description:线程等待后中止该线程
 * @date:18/5/27
 */
public class WaitNotifyInterupt {
    static Object object = new Object();

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("----begin----");
                try {
                    // 阻塞当前线程
                    object.wait();
                    System.out.println("-----end-----");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        try {
            //thread调用了共享对象object的wait()方法后阻塞挂起了自己，然后主线程在休眠1s后中断了thread线程,
            //可知中断后thread在obj.wait()处抛出了 java.lang.IllegalMonitorStateException 异常后返回后终止
            Thread.sleep(1000);
            System.out.println("---begin interrupt threadA---");
            thread.interrupt();
            System.out.println("---end interrupt threadA---");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
