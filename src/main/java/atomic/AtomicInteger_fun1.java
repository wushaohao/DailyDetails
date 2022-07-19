package atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author:wuhao
 * @description:AtomicInteger自增长类方法测试
 * @date:18/9/27
 */
public class AtomicInteger_fun1 {

    private static AtomicInteger currentIndex = new AtomicInteger(0);

    public static void main(String[] args) {
        System.out.println("操作前的值是:" + currentIndex);
        //getAndIncrement():原子操作 获取操作之前的原始值    0
        System.out.println("getAndIncrement()之后的值是:" + currentIndex.getAndIncrement());
        //incrementAndGet():原子操作 获取操作完成后的值    2
        System.out.println("incrementAndGet()之后的值是:" + currentIndex.incrementAndGet());
    }
}
