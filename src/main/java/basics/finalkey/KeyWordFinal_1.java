package basics.finalkey;

/**
 * @author:wuhao
 * @description:final参数的问题
 * @date:18/10/23
 */
public class KeyWordFinal_1 {
    /**
     * changeValue方法中的参数i用final修饰之后，就不能在方法中更改变量i的值了
     * 方法changeValue和main方法中的变量i根本就不是一个变量，因为java参数传递采用的是值传递，
     * 对于基本类型的变量，相当于直接将变量进行了拷贝。所以即使没有final修饰的情况下，在方法内部改变了变量i的值也不会影响方法外的i
     *
     * @param args
     */
    public static void main(String[] args) {
        KeyWordFinal_1 test = new KeyWordFinal_1();
        int i = 0;
        test.changeValue(i);
        System.out.println(i);
    }

    public void changeValue(final int i) {
        /* final 参数值不可改变 */
//        i++;
        System.out.println("i的值是:" + i);
    }
}
