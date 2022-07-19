package javaDetails;

/**
 * @Author:wuhao
 * @Description:Java中方法的重写与成员变量的隐藏 在Java的子类与父类中有两个名称、参数列表都相同的方法的情况。由于他们具有相同的方法签名，所以子类中的新方法将覆盖父类中原有的方法。
 * Java中，成员变量并不会被重写。这里就有另外一个词：隐藏
 * 在一个类中，子类中的成员变量如果和父类中的成员变量同名，那么即使他们类型不一样，只要名字一样。父类中的成员变量都会被隐藏。在子类中，
 * 父类的成员变量不能被简单的用引用来访问。而是，必须从父类的引用获得父类被隐藏的成员变量，一般来说，我们不推荐隐藏成员变量，因为这样会使代码变得难以阅读.
 * 其实，简单来说，就是子类不会去重写覆盖父类的成员变量，所以成员变量的访问不能像方法一样使用多态去访问
 * @Date:17/9/29
 */
public class FieldOverriding {

    public static void main(String[] args) {
        Sub sub = new Sub();
        System.out.println("sub.s:" + sub.s);
        System.out.println("sub.say:" + sub.say());

        Super sup = new Sub();
        System.out.println("sup.s:" + sup.s);
        System.out.println("sup.say:" + sup.say());
//        System.out.println(((Super)sub).s);
    }


}


class Super {
    String s = "Super";

    String say() {
        return "say hello super";
    }
}

class Sub extends Super {
    String s = "Sub";

    String say() {
        return "say hello sub";
    }
}
