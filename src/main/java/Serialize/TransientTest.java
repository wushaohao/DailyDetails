package Serialize;

import java.io.*;

/**
 * Created by wuhao on 16/12/28.
 * <p>
 * 1）一旦变量被transient修饰，变量将不再是对象持久化的一部分，该变量内容在序列化后无法获得访问。
 * <p>
 * 2）transient关键字只能修饰变量，而不能修饰方法和类。注意，本地变量是不能被transient关键字修饰的。变量如果是用户自定义类变量，则该类需要实现Serializable接口。
 * <p>
 * 3）被transient关键字修饰的变量不再能被序列化，一个静态变量不管是否被transient修饰，均不能被序列化
 * <p>
 * 第三点可能有些人很迷惑，因为发现在User类中的username字段前加上static关键字后，程序运行结果依然不变，
 * 即static类型的username也读出来为“Harden”了，这不与第三点说的矛盾吗？实际上是这样的：第三点确实没错（一个静态变量不管是否被transient修饰，均不能被序列化），
 * 反序列化后类中static型变量username的值为当前JVM中对应static变量的值，这个值是JVM中的不是反序列化得出的.
 */
public class TransientTest {

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("Harden");
        user.setPassword("123456");

        System.out.println("read before serializable ");
        System.out.println("userName的值是:" + user.getUserName());
        System.out.println("userPassword的值是:" + user.getPassword());


        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("./documents/user.txt"));
            objectOutputStream.writeObject(user);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        User.userName = "Curry";
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("./documents/user.txt"));
            user = (User) objectInputStream.readObject();
            System.out.println("read after serializable ");
            System.out.println("userName的值是:" + user.getUserName());
            System.out.println("userPassword的值是:" + user.getPassword());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
