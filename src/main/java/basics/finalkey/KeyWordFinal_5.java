package basics.finalkey;

/**
 * @author:wuhao
 * @description:final参数的问题
 * @date:18/10/25
 */
public class KeyWordFinal_5 {
    public static void main(String[] args) {
        KeyWordFinal_5 test = new KeyWordFinal_5();
        StringBuffer sb = new StringBuffer("hello");
        test.changeValue(sb);
        System.out.println(sb);
//        test.changeValue2(sb);
//        System.out.println(sb);
    }

    public void changeValue(final StringBuffer buffer) {
        //final修饰引用类型的参数,不能再让其指向其他对象,但是对其所指向的内容是可以更改的
        //buffer = new StringBuffer("hi");
        buffer.append("world");
    }

    /**
     * 同时在changeValue中让buffer指向了其他对象，并不会影响到main方法中的buffer，原因在于java采用的是值传递，对于引用变量，
     * 传递的是引用的值，也就是说让实参和形参同时指向了同一个对象，因此让形参重新指向另一个对象对实参并没有任何影响
     *
     * @param buffer
     */
    public void changeValue2(StringBuffer buffer) {
        //buffer重新指向另一个对象
        buffer = new StringBuffer("hi");
        buffer.append("world");
        System.out.println(buffer);
    }
}
