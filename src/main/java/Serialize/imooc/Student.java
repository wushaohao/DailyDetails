package Serialize.imooc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author:wuhao
 * @description:bean对象
 * @date:18/8/21
 */
public class Student implements Serializable {
    private String stuName;
    /**
     * transient关键字修饰的元素不会进行jvm默认的序列化
     */
    private transient int age;
    private String stuId;

    public Student() {
    }

    public Student(String stuName, int age, String stuId) {
        this.stuName = stuName;
        this.age = age;
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuName='" + stuName + '\'' +
                ", age=" + age +
                ", stuId='" + stuId + '\'' +
                '}';
    }

    /**
     * 自身完成序列化操作
     *
     * @param s
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream s) throws IOException {
        //序列化jvm能够默认序列化元素进行徐立户
        s.defaultWriteObject();
        // 自己完成age序列化
        s.writeObject(age);
    }

    /**
     * 资深完成反序列化操作
     *
     * @param s
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        // 把jvm虚拟机默认反序列化
        s.defaultReadObject();
        // 自己完成age的反序列化工作
        this.age = (int) s.readObject();
    }
}
