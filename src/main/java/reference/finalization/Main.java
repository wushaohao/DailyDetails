package reference.finalization;


/**
 * @author:wuhao
 * @description:
 * @date:18/8/7
 */
public class Main {
    public static class User {
        public static Resource res = null;
    }

    private static class Resource {
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("执行finalize方法，并拯救对象");
            User.res = this;
        }
    }

    public static void main(String[] args) {
        // 使用者占用资源
        User.res = new Resource();
        // 使用者释放资源，这时上面new的Resource对象已经没有强引用，可以被垃圾回收
        User.res = null;
        // 此处如果执行了finalize方法后，对象即可被拯救成功，又有了强引用
        System.gc();
        try {
            // gc线程优先级比较低，此处等待垃圾回收
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(User.res);

        // 再次释放资源，让资源被回收(此时已经不能被拯救，因为finalize方法只会被调用一次）
        User.res = null;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(User.res);
    }
}
