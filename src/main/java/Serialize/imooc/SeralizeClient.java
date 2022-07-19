package Serialize.imooc;

import java.io.*;

/**
 * @author:wuhao
 * @description:序列化请求客户端
 * @date:18/8/21
 */
public class SeralizeClient {
    private final static String filePath = "student.txt";

    /**
     * 序列化对象
     *
     * @param filePath
     */
    public static void seralizeObj(String filePath) {
        Student student = new Student("Hulk", 20, "10001");
        ObjectOutputStream oos = null;
        try {
            // 序列化对象
            oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(student);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 反序列化对象
     *
     * @param filePath
     */
    public static void deSeralizeObj(String filePath) {
        ObjectInputStream ois = null;
        // 反序列化对象
        try {
            ois = new ObjectInputStream(new FileInputStream(filePath));
            Student student1 = (Student) ois.readObject();
            System.out.println("序列化后的是:" + student1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        seralizeObj(filePath);
        deSeralizeObj(filePath);
    }
}
