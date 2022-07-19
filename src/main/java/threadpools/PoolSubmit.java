package threadpools;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author:wuhao
 * @description:线程池submit和execute
 * @date:18/5/26
 */
public class PoolSubmit {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(10);

//        Future<String> future = pool.submit(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return "Hello";
//            }
//        });
//        String results = future.get();
//        System.out.println("执行结果是:" + results);

        Data data = new Data();
        //直接submit一个Runnable是拿不到返回值的，返回值就是null
        Future<Data> future = pool.submit(new MyRunnable(data), data);

        String results = future.get().getName();
        System.out.println("Runnable 运行结果是:" + results);

    }

    static class Data {
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class MyRunnable implements Runnable {
        private Data data;

        public MyRunnable(Data data) {
            this.data = data;
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
            data.setName("caicai");
        }
    }

}
