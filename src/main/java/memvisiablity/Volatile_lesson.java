package memvisiablity;

/**
 * @author:wuhao
 * @description:volatile变量
 * @date:18/8/16
 */
public class Volatile_lesson {
    private volatile int number = 0;

    public int getNumber() {
        return number;
    }

    public void increment() {
        /**
         * number++ 不是原子操作
         * 1.从内存中读取number的值
         * 2.执行number＋1
         * 3.将操作后的值刷到内存中去
         * 保证number自增操作的原子性:
         * 1.使用synchronized关键字
         * 2.使用ReentranLock
         * 3.使用AtomicInteger
         */
        this.number++;
    }

    public static void main(String[] args) {
        final Volatile_lesson volatile_lesson = new Volatile_lesson();
        for (int i = 0; i < 500; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    volatile_lesson.increment();
                }
            }).start();
        }

        //如果还有子线程在运行，主线程让出CPU资源，直到所有的子线程都运行完了，主线程再继续往下执行
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        //
        System.out.println("number:" + volatile_lesson.getNumber());
    }
}
