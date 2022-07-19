package designModels.Singleton;

import java.io.*;

/**
 * @author:wuhao
 * @description:序列化单例
 * @date:18/5/31
 */
public class SingleModel7 implements Serializable {
    private static boolean initialized = false;

    private SingleModel7() {
        synchronized (SingleModel6.class) {
            if (initialized == false) {
                initialized = !initialized;
            } else {
                throw new RuntimeException("单例已被破坏");
            }
        }
    }

    static class SingleObject {
        private static SingleModel7 INSTANCE = new SingleModel7();
    }

    public static SingleModel7 getInstance() {
        return SingleObject.INSTANCE;
    }

    public static void main(String[] args) {
        SingleModel7 instance1 = SingleModel7.getInstance();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
            out.writeObject(instance1);
            out.close();
            //deserialize from file to object
            ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"));
            SingleModel7 instance2 = (SingleModel7) in.readObject();
            in.close();
            System.out.println("instance1 hashCode=" + instance1.hashCode());
            System.out.println("instance2 hashCode=" + instance2.hashCode());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
