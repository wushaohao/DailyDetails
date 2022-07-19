package basics.finalkey;

/**
 * @author:wuhao
 * @description:final关键字2
 * @date:18/10/25
 */
public class KeyWordFinal_2 {
    /**
     * 当final变量是基本数据类型以及String类型时，如果在编译期间能知道它的确切值，则编译器会把它当做编译期常量使用。
     * 也就是说在用到该final变量的地方，相当于直接访问的这个常量，不需要在运行时确定。这种和C语言中的宏替换有点像。因此在上面的一段代码中，由于变量b被final修饰，
     * 因此会被当做编译器常量，所以在使用到b的地方会直接将变量b 替换为它的值。而对于变量d的访问却需要在运行时通过链接来进行
     *
     * @param args
     */
    public static void main(String[] args) {
        String a = "hello2";
        //不要以为某些数据是final就可以在编译期知道其值，通过变量b我们就知道了，在这里是使用getHello()方法对其进行初始化，他要在运行期才能知道其值。
        final String b = getHello();
        String c = b + 2;
        System.out.println(a == c);
    }

    public static String getHello() {
        return "hello";
    }
}
