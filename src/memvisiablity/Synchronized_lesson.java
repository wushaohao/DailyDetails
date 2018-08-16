package memvisiablity;

/**
 * @author:wuhao
 * @description:Synchronized 内存可见性
 * @date:18/8/16
 */
public class Synchronized_lesson {

    // 共享变量
    private boolean ready = false;
    private int result = 0;
    private int number = 1;

    //写操作
    public void write() {
        ready = true; //1.1
        number = 2; //1.2
    }

    //读操作
    public void read() {
        if (ready) {//2.1
            result = number * 3;//2.2
        }
        System.out.println("result的值是:" + result);
    }

    //内部线程类
    private class ReadWriteThread extends Thread {
        //根据构造方法中传入的flag参数 确定线程执行读操作还是写操作
        private boolean flag;
        public ReadWriteThread(boolean flag) {
            this.flag = false;
        }

        @Override
        public void run() {
            if (flag) {
                write();
            } else {
                read();
            }

        }
    }

    public static void main(String[] args) {
        /**
         * 可见性分析:
         * 执行顺序: 1.1-->2.1-->2.2-->1.2   result=3
         * 1.2-->2.1-->2.2-->1.1 result=0
         * 2.1 2.1也可重排序
         *
         */
        Synchronized_lesson lesson = new Synchronized_lesson();
        //启动线程执行写操作
        lesson.new ReadWriteThread(true).start();

        try {
            // 主线程休眠2s,确保写线程执行完成,写线程执行完成后主线程苏醒,读线程启动执行
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //启动线程执行读操作
        lesson.new ReadWriteThread(false).start();

    }
}
