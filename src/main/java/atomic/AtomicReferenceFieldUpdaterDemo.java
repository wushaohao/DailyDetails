package atomic;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author:wuhao
 * @description:AtomicReferenceFieldUpdater使用
 * @date:18/12/10
 */
public class AtomicReferenceFieldUpdaterDemo {
    static class Dog {
         volatile String name = "哈士奇";
    }

    public static void main(String[] args) {
        AtomicReferenceFieldUpdater updater = AtomicReferenceFieldUpdater.newUpdater(Dog.class, String.class, "name");
        Dog dog = new Dog();
        updater.compareAndSet(dog, dog.name, "金毛");
        System.out.println("修改后的狗名称是:" + dog.name);
    }

}
