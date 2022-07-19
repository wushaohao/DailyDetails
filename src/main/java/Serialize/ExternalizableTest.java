package Serialize;

import java.io.*;
import java.util.Map;

/**
 * Created by wuhao on 16/12/28.
 * 对象的序列化可以通过实现两种接口来实现，若实现的是Serializable接口，则所有的序列化将会自动进行，若实现的是Externalizable接口，
 * 则没有任何东西可以自动序列化，需要在writeExternal方法中进行手工指定所要序列化的变量，这与是否被transient修饰无关
 */
public class ExternalizableTest implements Externalizable {

    private transient String content = "是的,我将被序列化,不管我有么有被transient修饰";

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(content);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        content = (String) in.readObject();

    }

    public void main(String[] args) {
        ExternalizableTest externalizableTest = new ExternalizableTest();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("./documents/test.txt"));
            objectOutputStream.writeObject(externalizableTest);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException ee) {
            ee.printStackTrace();
        }

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("./documents/test.txt"));
            externalizableTest = (ExternalizableTest) objectInputStream.readObject();
            System.out.println("序列化后的content内容是:" + externalizableTest.content);
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
