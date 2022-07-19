package designModels.Singleton.nolock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author:wuhao
 * @description:CAS实现单例
 * @date:2019/12/19
 */
public class SingletonCAS {
    /**
     * 1.用CAS的好处在于不需要使用传统的锁机制来保证线程安全,CAS是一种基于忙等待的算法,依赖底层硬件的实现,相对于锁它没有线程切换和阻塞的额外消耗,可以支持较大的并行度
     * 2.CAS的一个重要缺点在于如果忙等待一直执行不成功(一直在死循环中),会对CPU造成较大的执行开销
     * 3.另外，如果N个线程同时执行到singleton = new Singleton();的时候，会有大量对象创建，很可能导致内存溢出
     */
    private static final AtomicReference instance = new AtomicReference();

    private SingletonCAS() {
    }

    public static SingletonCAS getInstance() {
        for (; ; ) {
            SingletonCAS singletonCAS = (SingletonCAS) instance.get();
            if (null != singletonCAS) {
                return singletonCAS;
            }
            singletonCAS = new SingletonCAS();
            if (instance.compareAndSet(null, singletonCAS)) {
                return singletonCAS;
            }
        }
    }
}
