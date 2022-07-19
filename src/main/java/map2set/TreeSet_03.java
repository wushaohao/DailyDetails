package map2set;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author:wuhao
 * @description:TreeSet探索3
 * @date:18/8/12
 */
public class TreeSet_03 {
    public static void main(String[] args) {
        TreeSet<String> treeSet = new TreeSet<>();

        treeSet.add("X");
        treeSet.add("W");
        treeSet.add("M");
        treeSet.add("Z");
        treeSet.add("C");
        treeSet.add("A");

        System.out.println("大小是:" + treeSet.size());

        Iterator<String> iterator = treeSet.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
