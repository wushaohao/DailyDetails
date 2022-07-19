package juc.threadstate;

import org.junit.Test;

/**
 * @author:wuhao
 * @description:wait/notify过程
 * @date:2019-08-06
 */
public class WaitingStateTest {
    @Test
    public void test() {
        class Toilet {
            /**
             * 纸张
             */
            int paperCount = 0;

            public void pee() {
                try {
                    Thread.sleep(21000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        Toilet toilet = new Toilet();
        // 乘客线程
        Thread[] passengers = new Thread[2];
        for (int i = 0; i < passengers.length; i++) {
            passengers[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (toilet) {
                        while (toilet.paperCount < 1) {
                            try {
                                toilet.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        // 使用一张纸
                        toilet.paperCount--;
                        toilet.pee();
                    }
                }
            });
        }

        // 乘务员线程
        Thread steward = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (toilet) {
                    //增加10张纸
                    toilet.paperCount += 10;
                    //通知在此对象上等待的线程
//                    toilet.notify();
                    //通知在此对象上等待的所有线程
                    toilet.notifyAll();
                }
            }
        });

        passengers[0].start();
        passengers[1].start();

        //确保已经执行了run方法
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 没有纸(条件不满足) 线程进入等待状态
        passengers[0].getState().equals(Thread.State.WAITING);
        passengers[1].getState().equals(Thread.State.WAITING);

        //乘务员线程启动
        steward.start();

        //确保纸张已经增加且通知
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 其中之一会得到锁，并执行 pee，但无法确定是哪个，所以用 "或 ||"
        // 注：因为 pee 方法中实际调用是 sleep， 所以很快就从 RUNNABLE 转入 TIMED_WAITING(sleep 时对应的状态)
        System.out.println(Thread.State.TIMED_WAITING.equals(passengers[0].getState()));
        System.out.println(Thread.State.TIMED_WAITING.equals(passengers[1].getState()));
    }
}
