package ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wuhao on 16/10/14.
 */
public class ThreadPoolTest3 implements Runnable{

     public static  void main(String[] args){

         ArrayBlockingQueue<Runnable> queue=new ArrayBlockingQueue<Runnable>(4);

         ThreadPoolExecutor executor=new ThreadPoolExecutor(2,6,1, TimeUnit.DAYS,queue);

         for (int i = 0; i < 10; i++) {
             executor.execute(new Thread(new ThreadPoolTest3(),"".concat(""+i)));
             int threadSize=queue.size();
             System.out.println("线程队列的大小为:"+threadSize);

             if(threadSize == 4){
                 queue.add(new Runnable() {//使用add 队列已满 会抛出异常
                     @Override
                     public void run() {
                         System.out.println("新线程是否可以进入队列");
                     }
                 });
             }
         }
         executor.shutdown();
     }

    @Override
    public void run() {
        System.out.println("当前的线程是:"+Thread.currentThread().getName());
         synchronized (this){
             try {
                 Thread.sleep(3000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
    }
}
