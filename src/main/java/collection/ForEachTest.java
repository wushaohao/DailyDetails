package collection;

import com.google.common.collect.ImmutableList;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

/**
 * @author:wuhao
 * @description:for增强遍历fail-fast测试demo
 * @date:2019/4/10
 */
public class ForEachTest {
    public static void main(String[] args) {
        List<String> userNames = ImmutableList.of("Curry", "Harden", "James", "Fang");

        System.out.println("for增强遍历");

        Iterator iterator = userNames.iterator();

        /**
         *
         * 在for增强遍历的时候不要使用集合的remove、add方法,否则会抛出ConcurrentModificationException异常
         * 因为Iterator.next()的时候是Iterator的实现类Itr(ArrayList的一个内部类),在初始化的时候就会使modCount=exceptedCount;
         * A:先来看看modCount和expectedModCount这两个变量的实际意义:
         * 1.modCount是ArrayList中的一个成员变量。它表示该集合实际被修改的次数。
         * 2.expectedModCount 是 ArrayList中的一个内部类——Itr中的成员变量。expectedModCount表示这个迭代器期望该集合被修改的次数。
         *   其值是在ArrayList.iterator方法被调用的时候初始化的。只有通过迭代器对集合进行操作，该值才会改变。
         * 3.Itr是一个Iterator的实现，使用ArrayList.iterator方法可以获取到的迭代器就是Itr类的实例。
         *
         * B:而在使用集合自身的remove、add操作时只会对modCount进行操作,而iterator.next()操作首先会执行checkForComodification()方法,也就是
         * final void checkForComodification() {
         *     if (modCount != expectedModCount)
         *        throw new ConcurrentModificationException();
         * }
         *  所以肯定会抛出ConcurrentModificationException异常也就是fail-fast异常
         *  所以在遍历操作中执行remove操作最好是使用iterator.remove方法这样会修改exceptedCount的值
         *
         */
        do {
            if (!iterator.hasNext()) {
                break;
            }
            String userName = (String) iterator.next();
            if ("James".equalsIgnoreCase(userName)) {
                userNames.remove(userName);
            }
        } while (true);
        System.out.println("操作后的集合是:" + userNames);

        /**
         * 解决办法
         * 1.使用普通for遍历
         */
        for (int i = 0; i < userNames.size(); i++) {

        }

        /**
         * 2.直接使用iterator进行remove/add操作
         * 直接使用Iterator提供的remove方法，那么就可以修改到expectedModCount的值。那么就不会再抛出异常了
         */
        while (iterator.hasNext()) {
            if (iterator.next().equals("James")) {
                iterator.remove();
            }
        }

        /**
         * 3.java8提供的filter过滤
         * Java 8中可以把集合转换成流，对于流有一种filter操作， 可以对原始 Stream 进行某项测试，通过测试的元素被留下来生成一个新 Stream
         */
        userNames=userNames.stream().filter(userName->!userName.equalsIgnoreCase("James")).collect(Collectors.toList());

        /**
         * 4.使用fail-safe集合类
         * 这样的集合容器在遍历时不是直接在集合内容上访问的，而是先复制原有集合内容，在拷贝的集合上进行遍历。
         * 基于拷贝内容的优点是避免了ConcurrentModificationException，但同样地，迭代器并不能访问到修改后的内容，
         * 即：迭代器遍历的是开始遍历那一刻拿到的集合拷贝，在遍历期间原集合发生的修改迭代器是不知道的
         */
        ConcurrentLinkedDeque<String> queues = new ConcurrentLinkedDeque<String>() {{
            add("Curry");
            add("James");
            add("Harden");
            add("Fang");
        }};
        for (String name : queues) {
            if ("James".equalsIgnoreCase(name)) {
                queues.remove();
            }
        }

        /**
         * 5.使用增强for循环其实也可以
         * 某个即将删除的元素只包含一个的话， 比如对Set进行操作，那么其实也是可以使用增强for循环的，只要在删除之后，立刻结束循环体，
         * 不要再继续进行遍历就可以了，也就是说不让代码执行到下一次的next方法(因为for增强遍历每次循环都是iterator.next()来判断是否有元素继续进行遍历,在执行
         * 完remove后直接跳出循环也就是不会出发iterator.next的检查机制)
         */
        for (String userName : userNames) {
            if (userName.equals("James")) {
                userNames.remove(userName);
                break;
            }
        }

    }
}
