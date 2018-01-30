package random;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 随机数 5-10
 * Random.nextInt(n);返回0-n之间的随机数 不包括n
 * nextInt（）是返回无范围的随机数
 * Created by wuhao on 16/11/26.
 */
public class Random_test {

    public static int getRandomNum(int minNum,int maxNum){
        Random random=new Random();
        int randomNum= random.nextInt((maxNum-minNum)+1)+minNum;
        return randomNum;
    }
    public static void main(String[] args){
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        final Random random=new Random();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Random_test.getRandomNum(5,10));
                    System.out.println("返回10以内的随机数是:"+random.nextInt(10));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }
}
