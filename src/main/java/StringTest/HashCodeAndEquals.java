package StringTest;

/**
 * @author:wuhao
 * @description:hashCode()、equals()方法使用
 * @date:18/12/17
 */
public class HashCodeAndEquals {
    /**
     * == : 它的作用是判断两个对象的地址是不是相等。即，判断两个对象是不是同一个对象。(基本数据类型==比较的是值，引用数据类型==比较的是内存地址)
     * <p>
     * equals() : 它的作用也是判断两个对象是否相等。但它一般有两种使用情况：
     * <p>
     * 情况1：类没有覆盖equals()方法。则通过equals()比较该类的两个对象时，等价于通过“==”比较这两个对象。
     * 情况2：类覆盖了equals()方法。一般，我们都覆盖equals()方法来两个对象的内容相等；若它们的内容相等，则返回true(即，认为这两个对象相等)。
     *
     * @param args
     */
    public static void main(String[] args) {
        // a 为一个引用
        String a = new String("ab");
        // b为另一个引用,对象的内容一样
        String b = new String("ab");
        // 放在常量池中
        String aa = "ab";
        // 从常量池中查找
        String bb = "ab";
        if (aa == bb) {
            // true
            System.out.println("aa==bb");
        }
        if (a == b) {
            // false，非同一对象
            System.out.println("a==b");
        }
        /**
         * 1.String中的equals方法是被重写过的，因为object的equals方法是比较的对象的内存地址，而String的equals方法比较的是对象的值。
         * 2.当创建String类型的对象时，虚拟机会在常量池中查找有没有已经存在的值和要创建的值相同的对象，如果有就把它赋给当前引用。
         * 如果没有就在常量池中重新创建一个String对象
         */
        if (a.equals(b)) {
            // true
            System.out.println("aEQb");
        }
        if (42 == 42.0) {
            // true
            System.out.println("true");
        }
    }
}
