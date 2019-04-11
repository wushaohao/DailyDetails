package atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author:wuhao
 * @description:AtomicReference测试类
 * @date:2019/4/10
 */
public class AtomicReferenceTest {
    public static void main(String[] args) {
        // 创建2个person对象
        Person person1 = new Person(101);
        Person person2 = new Person(102);

        // 新建AtomicReference对象 初始化它的值为person1
        AtomicReference atomicReference = new AtomicReference(person1);

        // 通过CAS来设置atomicReference,如果atomicReference的值为person1则将其设置为person2
        atomicReference.compareAndSet(person1, person2);

        Person person3 = (Person) atomicReference.get();
        System.out.println("person3 is:" + person3);
        System.out.println("person3.equals(person1):" + person3.equals(person1));
    }
}

class Person {
    volatile int id;

    public Person(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                '}';
    }
}
