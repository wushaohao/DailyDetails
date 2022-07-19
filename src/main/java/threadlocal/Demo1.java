package threadlocal;

/**
 * @author wuhao
 * @date 17/6/6
 * 1）实际的通过ThreadLocal创建的副本是存储在每个线程自己的threadLocals中的；
 * 2）为何threadLocals的类型ThreadLocalMap的键值为ThreadLocal对象，因为每个线程中可有多个threadLocal变量，就像上面代码中的longLocal和stringLocal；
 * 3）在进行get之前，必须先set，否则会报空指针异常
 */
public class Demo1 {

    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();

    ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

    public void set() {
        longThreadLocal.set(Thread.currentThread().getId());
        stringThreadLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longThreadLocal.get();
    }

    public String getString() {
        return stringThreadLocal.get();
    }

    public static void main(String[] args) {
        final Demo1 d = new Demo1();

        d.set(); // 先set否则会泡空指针异常
        System.out.println("current thread name is " + Thread.currentThread().getName());
        System.out.println("first get long is " + d.getLong());
        System.out.println("first get string is " + d.getString());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                d.set();
                System.out.println("current thread name is " + Thread.currentThread().getName());
                System.out.println("current get long is " + d.getLong());
                System.out.println("current get string is " + d.getString());

            }
        });

        thread.start();


        System.out.println("final get long is " + d.getLong());
        System.out.println("final get string is " + d.getString());

    }
}
