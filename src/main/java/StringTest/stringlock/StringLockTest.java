package StringTest.stringlock;

import java.util.concurrent.CountDownLatch;

/**
 * @author:wuhao
 * @description:String字符串为什么不加锁锁测试
 * @date:18/8/8
 */
public class StringLockTest {
    private static int i = 0;

    public static void increase(String groupName) {
        synchronized (groupName) {
            i++;
        }
    }

    public static int getResult() {
        return i;
    }

    public static class TestRunnable implements Runnable {

        private CountDownLatch latch = null;

        public TestRunnable(CountDownLatch latch) {
            this.latch = latch;
        }

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
            StringBuilder sb = new StringBuilder("abc");
            for (int j = 0; j < 100; j++) {
                StringLockTest.increase(sb.toString());
            }
            latch.countDown();
        }


        public static void main(String[] args) {
            int threadNum = 10;
            CountDownLatch latch = new CountDownLatch(threadNum);

            for (int i = 0; i < threadNum; i++) {
                new Thread(new TestRunnable(latch)).start();
            }
            try {
                latch.await();
                System.out.println(StringLockTest.getResult());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
