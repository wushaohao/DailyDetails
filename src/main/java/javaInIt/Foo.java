package javaInIt;

/**
 * @Author:wuhao
 * @Description:java类初始化顺序
 * @Date:17/10/7
 */
public class Foo {

    /**
     * instance variable initalizer 实例变量初始化器
     */
    String s = "abc";

    /**
     * constructor 构造函数
     */
    public Foo() {
        System.out.println("construct called");
    }

    /**
     *  static initalizer 静态初始化器
     */
    static {
        System.out.println("static initializer called");
    }


    {
        System.out.println("instance initalizer called");
    }

    public static void main(String[] args) {
        new Foo();
        new Foo();
    }
}
