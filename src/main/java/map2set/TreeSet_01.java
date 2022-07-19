package map2set;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author:wuhao
 * @description:TreeSet探索
 * @date:18/8/11
 */
public class TreeSet_01 {
    /**
     * 排序方式：需要元素具备比较功能，所以元素需要实现Comparable接口，覆盖compareTo方法
     * 如何保证元素唯一性?其实参考的就是比较方法compareTo的返回值是否是0，是0，就是重复元素，不存
     * <p>
     * <p>
     * 创建TreeSet集合的两种方法：
     * 1.存入TreeSet集合中的元素对象实现Comparable接口，重写compareTo方法，使传入的对象具备比较功能
     * 2.自定义类实现Comparator接口，重写compare方法，在创建Set对象的时候，将实现了Comparator接口的comparator对象传入Set的构造方法中，使定义的TreeSet集合具备比较功能。
     */

    public static void main(String[] args) {
        /**
         *  Person对象如果没有实现Comparable接口 那么会抛出  java.lang.ClassCastException: map2set.Person cannot be cast to java.lang.Comparable 异常
         */
        TreeSet<Person1> treeSet = new TreeSet<>(new CompareByAgeOrName());

//        treeSet.add(new Person(10, "hulk1"));
//        treeSet.add(new Person(12, "hulk2"));
//        treeSet.add(new Person(14, "hulk3"));
//        treeSet.add(new Person(16, "hulk4"));
        treeSet.add(new Person1(10, "hulk22"));
        treeSet.add(new Person1(10, "hulk1"));
        treeSet.add(new Person1(14, "hulk333"));
        treeSet.add(new Person1(16, "hulk4444"));


        System.out.println("treeSet长度是:" + treeSet.size());

        Iterator<Person1> iterator = treeSet.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next().getName());
        }


    }


    static class CompareByAgeOrName implements Comparator<Person1> {


        /**
         * Compares its two arguments for order.  Returns a negative integer,
         * zero, or a positive integer as the first argument is less than, equal
         * to, or greater than the second.<p>
         * <p>
         * In the foregoing description, the notation
         * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
         * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
         * <tt>0</tt>, or <tt>1</tt> according to whether the value of
         * <i>expression</i> is negative, zero or positive.<p>
         * <p>
         * The implementor must ensure that <tt>sgn(compare(x, y)) ==
         * -sgn(compare(y, x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
         * implies that <tt>compare(x, y)</tt> must throw an exception if and only
         * if <tt>compare(y, x)</tt> throws an exception.)<p>
         * <p>
         * The implementor must also ensure that the relation is transitive:
         * <tt>((compare(x, y)&gt;0) &amp;&amp; (compare(y, z)&gt;0))</tt> implies
         * <tt>compare(x, z)&gt;0</tt>.<p>
         * <p>
         * Finally, the implementor must ensure that <tt>compare(x, y)==0</tt>
         * implies that <tt>sgn(compare(x, z))==sgn(compare(y, z))</tt> for all
         * <tt>z</tt>.<p>
         * <p>
         * It is generally the case, but <i>not</i> strictly required that
         * <tt>(compare(x, y)==0) == (x.equals(y))</tt>.  Generally speaking,
         * any comparator that violates this condition should clearly indicate
         * this fact.  The recommended language is "Note: this comparator
         * imposes orderings that are inconsistent with equals."
         *
         * @param o1 the first object to be compared.
         * @param o2 the second object to be compared.
         * @return a negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater than the
         * second.
         * @throws NullPointerException if an argument is null and this
         *                              comparator does not permit null arguments
         * @throws ClassCastException   if the arguments' types prevent them from
         *                              being compared by this comparator.
         */
        @Override
        public int compare(Person1 o1, Person1 o2) {

            return o1.getAge() - o2.getAge() == 0 ? o1.getName().length() - o2.getName().length() == 0 ? o1.getName().length() : o1.getName().length() - o2.getName().length() : o1.getAge() - o2.getAge();
        }
    }
}
