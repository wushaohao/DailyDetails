package ThreadPool.threadProgram;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by wuhao on 17/5/8.
 * CyclicBarrier提供2个构造器：
 * 参数parties指让多少个线程或者任务等待至barrier状态；参数barrierAction为当这些线程都达到barrier状态时会执行的内容。
 * public CyclicBarrier(int parties, Runnable barrierAction) {}
 * public CyclicBarrier(int parties) {}
 *
 * public int await() throws InterruptedException, BrokenBarrierException { };//挂起当前线程，直至所有线程都到达barrier状态再同时执行后续任务；
   public int await(long timeout, TimeUnit unit)throws InterruptedException,BrokenBarrierException,TimeoutException { };//让这些线程等待至一定的时间，如果还有线程没有到达barrier状态就直接让到达barrier的线程执行后续任务
 */
public class cyclicBarrier_02 {

    static class readNum implements Runnable{
        private int id;
        private CyclicBarrier cyclicBarrier;

        public readNum(int id,CyclicBarrier cyclicBarrier){
            this.id=id;
            this.cyclicBarrier=cyclicBarrier;
        }

        @Override
        public void run() {
            synchronized (this){
                System.out.println("id:"+id);
                try {
                    cyclicBarrier.await();
                    System.out.println("Threads "+id+"end,other continue...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("Threads  wait end..");
            }
        });

        for (int i = 0; i < 5; i++) {
            new Thread(new cyclicBarrier_02.readNum(i,cyclicBarrier)).start();
        }

    }
}
