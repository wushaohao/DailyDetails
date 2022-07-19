package atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author:wuhao
 * @description:AtomicReference原子实例操作
 * @date:18/12/11
 */
public class AtomicReferenceV1 {
    private static AtomicReference<Integer> money = new AtomicReference<>();

    public static void main(String[] args) {
        // 设置初始账户值
        money.set(19);

        //模拟多个线程同时更新后台数据库，为用户充值
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        while (true) {
                            Integer m = money.get();
                            if (m < 20) {
                                if (money.compareAndSet(m, m + 20)) {
                                    System.out.println("余额小于20元，充值成功，余额:" + money.get() + "元");
                                    break;
                                }
                            } else {
                                System.out.println("余额大于20元,暂不充值");
                                break;
                            }
                        }

                    }

                }
            }).start();


            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        while (true) {
                            Integer m = money.get();
                            if (m > 10) {
                                System.out.println("大于10元");
                                if (money.compareAndSet(m, m - 10)) {
                                    System.out.println("成功消费10元，余额:" + money.get());
                                    break;
                                }
                            } else {
                                System.out.println("没有足够金额");
                                break;
                            }
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }


    }
}
