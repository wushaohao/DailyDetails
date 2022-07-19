package juc.threadstate;

import org.junit.Test;

/**
 * @author:wuhao
 * @description:Thread线程状态测试类
 * @date:2019-08-06
 */
public class ThreadStateTest {
    @Test
    public void test() {
        class Toilet {
            public void pee() {
                try {
                    Thread.sleep(21000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        Toilet toilet = new Toilet();

        Thread passenger1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (toilet) {
                    toilet.pee();
                }
            }
        });

        Thread passenger2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (toilet) {
                    toilet.pee();
                }
            }
        });

        // 确保线程1启动
        passenger1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 确保线程2启动
        passenger2.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(passenger2.getState().equals(Thread.State.BLOCKED));
    }


}
