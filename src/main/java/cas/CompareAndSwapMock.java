package cas;

import java.util.Random;

/**
 * @author:wuhao
 * @description:模拟CAS机制
 * @date:18/10/18
 */
public class CompareAndSwapMock {
    private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized int compareAndSwap(int exceptedValue, int newValue) {
        //获取旧值
        int oldValue = value;

        if (exceptedValue == oldValue) {
            this.value = newValue;
        }

        // 返回修改之前的值
        return oldValue;
    }

    // 判断是否设置成功
    public synchronized boolean compareAndSet(int exceptedValue, int newValue) {
        return exceptedValue == compareAndSwap(exceptedValue, newValue);
    }

    public static void main(String[] args) {
        final CompareAndSwapMock casMock = new CompareAndSwapMock();

        final Random random = new Random();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int exceptedValue = casMock.get();
                    boolean b = casMock.compareAndSet(exceptedValue, random.nextInt(100));
                    System.out.println("交换结果是:" + b);
                }
            }).start();
        }
    }
}
