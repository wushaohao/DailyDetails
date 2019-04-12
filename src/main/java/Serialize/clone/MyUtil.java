package Serialize.clone;

import lombok.Data;

import java.io.*;

/**
 * @author:wuhao
 * @description:深度克隆
 * @date:2019/4/11
 */
public class MyUtil {
    public MyUtil() {
        throw new AssertionError();
    }

    /**
     * ObjectOutputStream将Java对象的基本数据类型和图形写入OutputStream。可以使用ObjectInputStream读取（重构）对象。通过在流中使用文件可以实现对象的持久存储。如果流是网络套接字流，
     * 则可以在另一台主机上或另一个进程中重构对象。
     * 只能将支持java.io.Serializable接口的对象写入流中。每个serializable对象的类都被编码，编码内容包括类名和类签名、对象的字段值和数组值，以及从初始对象中引用的其他所有对象的闭包。
     * ByteArrayOutputStream类实现了一个输出流，其中的数据被写入一个byte数组。缓冲区会随着数据的不断写入而自动增长。可使用toByteArray()和toString()获取数据。
     * @param object
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T extends Serializable> T clone(T object) throws Exception {
        // ByteArrayOutputStream把内存中的数据读到字节数组中
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(object);

        // ByteArrayInputStream又把字节数组中的字节以流的形式读出,实现了对同一个字节数组的操作
        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);
        return (T) ois.readObject();
        // 说明：调用ByteArrayInputStream或ByteArrayOutputStream对象的close方法没有任何意义
        // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外部资源（如文件流）的释放
    }

    public static void main(String[] args) throws Exception {
        Person p1 = new Person("郭靖", 33, new Car("Benz", 300));
        // 深度克隆
        Person p2 = MyUtil.clone(p1);
        p2.getCar().setBrand("BYD");
        // 修改克隆的Person对象p2关联的汽车对象的品牌属性
        // 原来的Person对象p1关联的汽车不会受到任何影响
        // 因为在克隆Person对象时其关联的汽车对象也被克隆了
        System.out.println("p1:"+p1);
        System.out.println("p2:"+p2);
    }
}
@Data
class Person implements Serializable {
    private static final long serialVersionUID = -9102017020286042305L;
    // 姓名
    private String name;
    // 年龄
    private int age;
    // 座驾
    private Car car;

    public Person(String name, int age, Car car) {
        this.name = name;
        this.age = age;
        this.car = car;
    }
}

@Data
class Car implements Serializable {
    private static final long serialVersionUID = -5713945027627603702L;

    // 品牌
    private String brand;
    // 最高时速
    private int maxSpeed;

    public Car(String brand, int maxSpeed) {
        this.brand = brand;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "Car [brand=" + brand + ", maxSpeed=" + maxSpeed + "]";
    }
}
