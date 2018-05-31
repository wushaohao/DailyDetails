package designModels.Singleton;

import java.io.*;

/**
 * @author:wuhao
 * @description:序列化单例不会被破坏
 * @date:18/5/31
 */
public class SingleModel8 implements Serializable{
    private static boolean initialized = false;

    private SingleModel8() {
        synchronized (SingleModel6.class) {
            if (initialized == false) {
                initialized = !initialized;
            } else {
                throw new RuntimeException("单例已被破坏");
            }
        }
    }

    static class SingleObject{
        private static SingleModel8 INSTANCE = new SingleModel8();
    }

    public static SingleModel8 getInstance() {
        return SingleObject.INSTANCE;
    }

    /**
     * 这表示此时已能保证序列化和反序列化的对象是一致的
     * @return
     */
    private Object readResolve() {
        return getInstance();
    }

    public static void main(String[] args) {
        SingleModel8 instance1 = SingleModel8.getInstance();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
            out.writeObject(instance1);
            out.close();
            //deserialize from file to object
            ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"));
            SingleModel8 instance2 = (SingleModel8)in.readObject();
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
