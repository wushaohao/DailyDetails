package StringTest.stringlock;

/**
 * @author:wuhao
 * @description:String字符串拼接优化效率
 * @date:18/12/13
 */
public class StringV1 {
    private static int time = 50000;

    /**
     * String、StringBuilder、StringBuffer三者的执行效率：
     * <p>
     * StringBuilder > StringBuffer > String
     * <p>
     * 当然这个是相对的，不一定在所有情况下都是这样。
     * <p>
     * 比如String str = "hello"+ "world"的效率就比 StringBuilder st  = new StringBuilder().append("hello").append("world")要高。
     * <p>
     * 因此，这三个类是各有利弊，应当根据不同的情况来进行选择使用：
     * <p>
     * 当字符串相加操作或者改动较少的情况下，建议使用 String str="hello"这种形式；
     * <p>
     * 当字符串相加操作较多的情况下，建议使用StringBuilder，如果采用了多线程，则使用StringBuffer。
     *
     * @param args
     */
    public static void main(String[] args) {
        testString();
        testOptimalString();
    }

    /**
     * 对于直接相加字符串，效率很高，因为在编译器便确定了它的值，也就是说形如"I"+"love"+"java"; 的字符串相加，在编译期间便被优化成了"Ilovejava"。这个可以用javap -c命令反编译生成的class文件进行验证。
     * 对于间接相加（即包含字符串引用），形如s1+s2+s3; 效率要比直接相加低，因为在编译器不会对引用变量进行优化
     */
    public static void testString() {
        String s = "";
        long begin = System.currentTimeMillis();
        for (int i = 0; i < time; i++) {
            s += "java";
        }
        long over = System.currentTimeMillis();
        System.out.println("操作" + s.getClass().getName() + "类型使用的时间为：" + (over - begin) + "毫秒");
    }

    public static void testOptimalString() {
        String s = "";
        long begin = System.currentTimeMillis();
        for (int i = 0; i < time; i++) {
            StringBuilder sb = new StringBuilder(s);
            sb.append("java");
            s = sb.toString();
        }
        long over = System.currentTimeMillis();
        System.out.println("模拟JVM优化操作的时间为：" + (over - begin) + "毫秒");
    }

    /**
     * 在String类中，intern方法是一个本地方法，在JAVA SE6之前，intern方法会在运行时常量池中查找是否存在内容相同的字符串，
     * 如果存在则返回指向该字符串的引用，如果不存在，则会将该字符串入池，并返回一个指向该字符串的引用。因此，a和d指向的是同一个对象
     */
    public static void testCommon() {
        String a = "hello";
        String b = new String("hello");
        String c = new String("hello");
        String d = b.intern();

        System.out.println(a == b);
        System.out.println(b == c);
        System.out.println(b == d);
        System.out.println(a == d);

        /**
         *
         * 1的效率比2的效率要高，1中的"love"+"java"在编译期间会被优化成"lovejava"，而2中的不会被优化。下面是两种方式的字节码：
         * 通过javap -c 变异生成的字节码文件可以看出:在1中只进行了一次append操作，而在2中进行了两次append操作
         */

        String str1 = "I";
        //str1 += "love"+"java";     1
        str1 = str1 + "love" + "java"; // 2
    }
}
