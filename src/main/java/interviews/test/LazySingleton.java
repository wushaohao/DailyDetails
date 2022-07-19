package interviews.test;

public class LazySingleton {
    private static boolean initialized = false;

    private LazySingleton() {
        synchronized (LazySingleton.class) {
            if (initialized == false) {
                initialized = !initialized;
            } else {
                throw new RuntimeException("单例已被破坏");
            }
        }
    }

    static class SingletonHolder {
        private static final LazySingleton instance = new LazySingleton();
    }

    public static LazySingleton getInstance() {
        return SingletonHolder.instance;
    }

    public static String get(String str) {
        String L = "ab";
        String[] arr = str.split(L);
        System.out.println(arr.length);
        return arr.length + "L";

    }
}



