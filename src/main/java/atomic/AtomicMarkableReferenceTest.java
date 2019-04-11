package atomic;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author:wuhao
 * @description:AtomicMarkableReference降低ABA问题发生几率但是不能解决ABA问题
 * @date:2019/4/10
 */
public class AtomicMarkableReferenceTest {
    private static final AtomicMarkableReference<String> ATOMIC_MARKABLE_REFERENCE = new AtomicMarkableReference<>("abc", false);

    public static void main(String[] args) {
        //假设以下操作由不同线程执行
        //线程1 获取到mark状态为false，原始值为“abc”
        System.out.println("mark:"+ATOMIC_MARKABLE_REFERENCE.isMarked());

        boolean thread1 = ATOMIC_MARKABLE_REFERENCE.isMarked();
        //线程3获取到mark状态为false ，原始值为“abc”
        System.out.println("mark:"+ATOMIC_MARKABLE_REFERENCE.isMarked());

        boolean thread2 = ATOMIC_MARKABLE_REFERENCE.isMarked();
        ////线程1 更改abc为abc2
        System.out.println("change result:" + ATOMIC_MARKABLE_REFERENCE.compareAndSet("abc", "abc2", thread1, !thread1));

        //线程2获取到mark状态为true ，原始值为“abc2”
        System.out.println("mark:" + ATOMIC_MARKABLE_REFERENCE.isMarked());

        boolean thread3 = ATOMIC_MARKABLE_REFERENCE.isMarked();
        //线程2 更改abc2为abc
        System.out.println("change result:" + ATOMIC_MARKABLE_REFERENCE.compareAndSet("abc2", "abc", thread3, !thread3));

        //线程3更改abc为abc2
        System.out.println("change result:" + ATOMIC_MARKABLE_REFERENCE.compareAndSet("abc", "abc2", thread2, !thread2));

        //按照上面的执行顺序，3此修改都修改成功了，线程1与线程2拿到的mark状态都是false，他俩要做的操作都是将“abc”更改为“abc2”， 只是线程2在线程1和线程3中间做了一次更改，最后线程2做操作的时候并没有感知到线程1与线程3的更改；
    }
}
