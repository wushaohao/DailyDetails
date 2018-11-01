package google.guava.sort;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import google.guava.bean.Person;

/**
 * @author:wuhao
 * @description:Ordering排序类
 * @date:18/11/1
 */
public class SortDemo {
    public static void main(String[] args) {
        Person person = new Person(1);
        Person person2 = new Person(2);

        person.setAge(25);
        person2.setAge(23);
        person.setName("Hulk");
        person2.setName("Spider");

        Ordering<Person> byOrdering = Ordering.natural().nullsFirst().onResultOf(new Function<Person, String>() {
            @Override
            public String apply(Person person) {
                return String.valueOf(person.getAge());
            }
        });

        byOrdering.compare(person, person2);
        // person的年龄比person2大 所以输出2
        System.out.println(byOrdering.compare(person,person2));

    }
}
