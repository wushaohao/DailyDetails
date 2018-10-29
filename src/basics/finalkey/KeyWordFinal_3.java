package basics.finalkey;

/**
 * @author:wuhao
 * @description:final关键字3:当用final作用于类的成员变量时，成员变量（注意是类的成员变量，局部变量只需要保证在使用之前被初始化赋值即可）
 * 必须在定义时或者构造器中进行初始化赋值，而且final变量一旦被初始化赋值之后，就不能再被赋值了
 * @date:18/10/25
 */
public class KeyWordFinal_3 {
    /**
     * 当final变量是基本数据类型以及String类型时，如果在编译期间能知道它的确切值，则编译器会把它当做编译期常量使用。
     * 也就是说在用到该final变量的地方，相当于直接访问的这个常量，不需要在运行时确定。这种和C语言中的宏替换有点像。因此在下面的一段代码中，由于变量b被final修饰，
     * 因此会被当做编译器常量，所以在使用到b的地方会直接将变量b 替换为它的值。而对于变量d的访问却需要在运行时通过链接来进行
     * @param args
     */
    public static void main(String[] args) {
        String a = "hello2";
        final String b = "hello";
        String d = "hello";
        String c = b + 2;
        String e = d + 2;
        System.out.println(a == c);
        System.out.println(a == e);
    }
}
