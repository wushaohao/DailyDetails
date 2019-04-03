package javaDetails.integer;

/**
 * @author:wuhao
 * @description:Integer自动拆箱装箱
 * @date:2019/4/3
 */
public class IntegerMain1 {
    public static void main(String[] args) {
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;
        /**
         * 在自动装箱的时候通过valueOf方法创建Integer对象的时候,如果数值在[-127,128]之间,便返回
         * 指向IntegerCache.cache中已存在的对象的引用;否则创建一个新对象。
         *
         * 所以i1和i2的数值为100，因此会直接从cache中取已经存在的对象，所以i1和i2指向的是同一个对象，而i3和i4则是分别指向不同的对象
         */
        //true
        System.out.println(i1==i2);
        //false
        System.out.println(i3==i4);

        Double d1 = 100.0;
        Double d2 = 100.0;
        Double d3 = 200.0;
        Double d4 = 200.0;
        //false
        System.out.println(d1 == d2);
        //false
        System.out.println(d3 == d4);


        Boolean b1 = false;
        Boolean b2 = false;
        Boolean b3 = true;
        Boolean b4 = true;
        //true
        System.out.println(b1==b2);
        //true
        System.out.println(b3==b4);

        /**
         * 当 "=="运算符的两个操作数都是 包装器类型的引用,则是比较指向的是否是同一个对象
         * 而如果其中有一个操作数是表达式（即包含算术运算）则比较的是数值（即会触发自动拆箱的过程
         * 另外，对于包装器类型，equals方法并不会进行类型转换
         */
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        Long h = 2L;
        /**
         * 第一个和第二个输出结果没有什么疑问。第三句由于  a+b包含了算术运算，因此会触发自动拆箱过程（会调用intValue方法），
         * 因此它们比较的是数值是否相等。而对于c.equals(a+b)会先触发自动拆箱过程，再触发自动装箱过程，也就是说a+b，会先各自调用intValue方法，
         * 得到了加法运算后的数值之后，便调用Integer.valueOf方法，再进行equals比较。同理对于后面的也是这样，
         * 不过要注意倒数第二个和最后一个输出的结果（如果数值是int类型的，装箱过程调用的是Integer.valueOf；如果是long类型的，装箱调用的Long.valueOf方法）。
         */
        //true
        System.out.println(c==d);
        //false
        System.out.println(e==f);
        //true
        System.out.println(c==(a+b));
        //false
        System.out.println(c.equals(a+b));
        //true
        System.out.println(g==(a+b));
        //false
        System.out.println(g.equals(a+b));
        //true
        System.out.println(g.equals(a+h));
    }
}
