package threadlocal;

/**
 * @author wuhao
 * @date 17/6/6
 */
public class Demo2 {

    ThreadLocal<Long> longThreadLocal = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return Thread.currentThread().getId();
        }
    };

    ThreadLocal<String> stringThreadLocal = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return Thread.currentThread().getName();
        }
    };


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
        final Demo2 d = new Demo2();

        //d.set(); // 可以不设置set
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

        d.getTest();
    }

    public Demo2 getTest() {
        System.out.println("this  is " + this);
        return this;
    }
}
