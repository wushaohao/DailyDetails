package StringTest.stringlock;

/**
 * @author:wuhao
 * @description:StringBuffer、StringBuilder、String的区别
 * @date:18/12/13
 */
public class StringDiff {
    /**
     * String：适用于少量的字符串操作的情况
     * <p>
     * StringBuilder：适用于单线程下在字符缓冲区进行大量操作的情况
     * <p>
     * StringBuffer：适用多线程下在字符缓冲区进行大量操作的情况
     *
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 无论是subString操作、concat还是replace操作都不是在原有的字符串上进行的，而是重新生成了一个新的字符串对象。也就是说进行这些操作后，最原始的字符串并没有被改变。
         * ps:对String对象的任何改变都不影响到原对象，相关的任何change操作都会生成新的对象
         *
         * 如果运行这段代码会发现先输出“abc”，然后又输出“abcde”，好像是str这个对象被更改了，其实，这只是一种假象罢了，
         * JVM对于这几行代码是这样处理的，首先创建一个String对象str，并把“abc”赋值给str，然后在第三行中，其实JVM又创建了一个新的对象也名为str，
         * 然后再把原来的str的值和“de”加起来再赋值给新的str，而原来的str就会被JVM的垃圾回收机制（GC）给回收掉了，所以，str实际上并没有被更改，
         * 也就是前面说的String对象一旦创建之后就不可更改了。
         * 所以，Java中对String对象进行的操作实际上是一个不断创建新的对象并且将旧的对象回收的一个过程，所以执行速度很慢
         */
        long start = System.currentTimeMillis();
        String str = "abc";
        str = str + "de";
        long end = System.currentTimeMillis();
        System.out.println("String的替换执行时常为:" + (end - start));


        /**
         * 在class文件中有一部分 来存储编译期间生成的 字面常量以及符号引用，这部分叫做class文件常量池，在运行期间对应着方法区的运行时常量池。
         *
         * String str1 = "hello world";和String str3 = "hello world"; 都在编译期间生成了 字面常量和符号引用，
         * 运行期间字面常量"hello world"被存储在运行时常量池（当然只保存了一份）。通过这种方式来将String对象跟引用绑定的话，
         * JVM执行引擎会先在运行时常量池查找是否存在相同的字面常量，如果存在，则直接将引用指向已经存在的字面常量；
         * 否则在运行时常量池开辟一个空间来存储该字面常量，并将引用指向该字面常量。
         *
         * 纵所周知，通过new关键字来生成对象是在堆区进行的，而在堆区进行对象生成的过程是不会去检测该对象是否已经存在的。
         * 因此通过new来创建对象，创建出的一定是不同的对象，即使字符串的内容是相同的。
         */
        String str1 = "hello world";
        String str2 = new String("hello world");
        String str3 = "hello world";
        String str4 = new String("hello world");
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        System.out.println(str2 == str4);


        /**
         * 而StringBuilder和StringBuffer的对象是变量，对变量进行操作就是直接对该对象进行更改，而不进行创建和回收的操作，所以速度要比String快很多
         * 在线程安全上，StringBuilder是线程不安全的，而StringBuffer是线程安全的
         * 如果一个StringBuffer对象在字符串缓冲区被多个线程使用时，StringBuffer中很多方法可以带有synchronized关键字，所以可以保证线程是安全的，
         * 但StringBuilder的方法则没有该关键字，所以不能保证线程安全，有可能会出现一些错误的操作。所以如果要进行的操作是多线程的，
         * 那么就要使用StringBuffer，但是在单线程的情况下，还是建议使用速度比较快的StringBuilder
         */
        StringBuffer sb = new StringBuffer();
        long start1 = System.currentTimeMillis();
        sb.append("abc").append("de");
        long end1 = System.currentTimeMillis();
        System.out.println("StringBuffer的拼接时常为:" + (end1 - start1));

    }
}
