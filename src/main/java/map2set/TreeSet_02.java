package map2set;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author:wuhao
 * @description:TreeSet探索2
 * @date:18/8/12
 */
public class TreeSet_02 {
    public static void main(String[] args) {
        TreeSet<Person> treeSet = new TreeSet<>();
        treeSet.add(new Person(10, "hulk22"));
        treeSet.add(new Person(10, "hulk1"));
        treeSet.add(new Person(14, "hulk333"));
        treeSet.add(new Person(16, "hulk4444"));


        System.out.println("treeSet长度是:" + treeSet.size());

        Iterator<Person> iterator = treeSet.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next().getName());
        }
    }
}
