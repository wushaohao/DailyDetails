package Serialize.imooc;

import java.io.*;

/**
 * @author:wuhao
 * @description:父类与子类构造函数序列化调用关系
 * @date:18/8/21
 */
public class ConstructSerialize {
    private final static String filePath = "testSeralize.txt";

    /**
     * 序列化对象
     *
     * @param filePath
     */
    public static void seralizeFoo(String filePath) {
        Foo2 student = new Foo2();
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
    public static void deSeralizeFoo(String filePath) {
        ObjectInputStream ois = null;
        // 反序列化对象
        try {
            ois = new ObjectInputStream(new FileInputStream(filePath));
            Foo2 foo = (Foo2) ois.readObject();
            /**
             * 子类继承的父类实现了序列化接口,不会调用父类的构造函数
             */
            System.out.println("Foo2序列化后的是:" + foo);
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


    /**
     * 序列化对象
     *
     * @param filePath
     */
    public static void seralizeBar(String filePath) {
        Bar2 student = new Bar2();
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
    public static void deSeralizeBar(String filePath) {
        ObjectInputStream ois = null;
        // 反序列化对象
        try {
            ois = new ObjectInputStream(new FileInputStream(filePath));
            Bar2 bar = (Bar2) ois.readObject();
            /**
             * 子类继承的父类没有实现序列化接口会调用构造函数
             */
            System.out.println("Bar2序列化后的是:" + bar);
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
        seralizeFoo(filePath);
        deSeralizeFoo(filePath);

        seralizeBar(filePath);
        deSeralizeBar(filePath);

    }
}

class Foo implements Serializable {
    public Foo() {
        System.out.println("Foo");
    }
}

class Foo1 extends Foo {
    public Foo1() {
        System.out.println("Foo1");
    }
}

class Foo2 extends Foo1 {
    public Foo2() {
        System.out.println("Foo2");
    }
}


class Bar {
    public Bar() {
        System.out.println("Bar");
    }
}

class Bar1 extends Bar implements Serializable {
    public Bar1() {
        System.out.println("Bar1");
    }
}

class Bar2 extends Bar1 {
    public Bar2() {
        System.out.println("Bar2");
    }
}
