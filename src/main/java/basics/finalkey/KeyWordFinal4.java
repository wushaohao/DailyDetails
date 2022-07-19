package basics.finalkey;

/**
 * @author:wuhao
 * @description:被final修饰的引用变量指向的对象内容可变吗
 * @date:18/10/25
 */
public class KeyWordFinal4 {
    public static void main(String[] args) {
        final MyClass myClass = new MyClass();
        //引用变量被final修饰之后，虽然不能再指向其他对象，但是它指向的对象的内容是可变的
        System.out.println(++myClass.i);
    }
}

class MyClass {
    public int i = 0;
}
