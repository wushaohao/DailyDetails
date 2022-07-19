package stream;

import jacksonJson.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author:wuhao
 * @description:java8流操作
 * @date:18/11/30
 */
public class StreamDemo {

    public static void main(String[] args) {
        List<String> lists = Arrays.asList("java8", "lambda", "stream");

        Stream<String> stringStream = lists.stream();
        Consumer<String> consumer = (x) -> System.out.println(x);
        stringStream.forEach(consumer);
        //流只能被消费一次
//        stringStream.forEach(consumer);
    }

    /**
     * filter判断用户中是男性的
     * Streams接口的filter方法，该操作会接受一个谓词（一个返回boolean的函数）作为参数，并返回一个包括所有符合谓词的元素的流
     * filter(Predicate<? super T> predicate)
     *
     * @param users
     * @return
     */
    public List<User> filterOgStream(List<User> users) {
        List<User> user1 = users.stream().filter(User::isMale).collect(Collectors.toList());

        List<User> user2 = users.stream().filter(u -> u.getSex().equals("male")).collect(Collectors.toList());

        Predicate<User> predicate = (u) -> u.getSex().equals("male");
        List<User> user3 = users.stream().filter(predicate).collect(Collectors.toList());

        return user1;
    }


    /**
     * distinct(): 去除流中重复的元素
     * 打印集合中的偶数，并且不能重复
     */
    public void distinctOfStream() {
        List<Integer> lists = Arrays.asList(1, 2, 3, 4, 2, 6, 4, 7, 8, 7);
        // 第一种解析
        lists.stream().filter(x -> x % 2 == 0).distinct().forEach(System.out::println);
        // 第二种解析
        Consumer<Integer> consumer = (x) -> System.out.println(x);
        lists.stream().filter(x -> x % 2 == 0).distinct().forEach(consumer);
    }

    /**
     * 该方法会返回一个不超过给定长度的流。如果流是有序的，则最多会返回前n个元素。 如果是无序的，如set，limit的结果不会以任何顺序排列
     * limit()：返回流中指定长度的流
     */
    public void limitOfStream() {
        List<Integer> lists = Arrays.asList(1, 2, 3, 4, 2, 6, 4, 7, 8, 7);

        // 获取　lists 中前三个元素, 有序
        // 123
        lists.stream().limit(3).forEach(System.out::println);
    }


    /**
     * skip(n):跳过前n个元素，返回n后面的元素
     */
    public void skipOfStream() {
        List<Integer> lists = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //跳过前5个元素，从第6个元素开始打印
        //678910
        lists.stream().skip(5).forEach(System.out::println);
    }

    /**
     * map()方法：
     * 它会接受一个函数作为参数。这个函数会被应用到每个元素上，并将其映射成一个新的元素
     * 注：map不是我们理解的集合Map，应该理解为映射，将一个值映射为另一个值
     * 如下的例子为：取出集合中用户的名字，返回一个名字集合
     *
     * @param users
     * @return
     */
    public List<String> mapOfStream(List<User> users) {
        List<String> userNames = users.stream().map(User::getUsername).collect(Collectors.toList());
        // 另一种写法
        Function<User, String> function = (user) -> user.getUsername();
        List<String> userNames2 = users.stream().map(function).collect(Collectors.toList());


        // 获取每个用户的名字的长度
        // 写法一
        List<Integer> userNameLength = users.stream().map(User::getUsername).map(String::length).collect(Collectors.toList());

        // 写法二
        Function<User, String> name = user -> user.getUsername();
        Function<String, Integer> length = s -> s.length();
        List<Integer> userNameLength2 = users.stream().map(name).map(length).collect(Collectors.toList());

        //写法三
        List<Integer> userNameLength3 = users.stream().map(s -> s.getUsername()).map(s -> s.length()).collect(Collectors.toList());

        return userNames;
    }

    /**
     * anyMatch(): 流中是否有一个元素能匹配给定的谓词，只要有一个能够匹配，就返回 true
     */
    public void anyMatchOfStream() {
        List<Integer> lists = Arrays.asList(1, 2, 3, 3, 4, 5);
        Stream<Integer> stream = lists.stream();
        if (stream.anyMatch(i -> i == 3)) {
            System.out.println("包含 3");
        } else {
            System.out.println("不包含 3");
        }
    }

    /**
     * 检查流中的元素是否都能匹配给定的谓词，只有所有的值和给定的谓词相等，才返回 true
     */
    public void allMatch() {
        List<Integer> lists = Arrays.asList(3, 3);
        if (lists.stream().anyMatch(i -> i == 3)) {
            System.out.println("完全匹配");
        } else {
            System.out.println("不完全匹配");
        }
    }

    /**
     * findAny方法将返回当前流中的任意元素。它可以与其他流操作结合使用
     *
     * @param users
     */
    public void findAnyOfStream(List<User> users) {
        Optional<User> user = users.stream().filter(u -> u.getSex().equals("male")).findAny();
    }

    /**
     * reduce() 操作可以实现从Stream中生成一个值，其生成的值不是随意的，而是根据指定的Lambda表达式
     */
    public void reduceOfStream() {
        List<Integer> lists = Arrays.asList(1, 2, 3, 3, 4, 5);

        // 元素的总和
        int sum = lists.stream().reduce(0, (x, y) -> x + y);
        Optional<Integer> sum2 = lists.stream().reduce(Integer::sum);
        System.out.println("sun=" + sum);
        System.out.println("sum2=" + sum2);

        // 求最大值
        int max = lists.stream().reduce(0, (x, y) -> x > y ? x : y);
        Optional<Integer> max2 = lists.stream().reduce(Integer::max);
        System.out.println("max=" + max);
        System.out.println("max2=" + max2);

        // 最小值
        int min = lists.stream().reduce(0, (x, y) -> x > y ? y : x);
        Optional<Integer> min2 = lists.stream().reduce(Integer::min);
        System.out.println("min=" + min);
        System.out.println("min2=" + min2);
    }

    /**
     * java 8引入了两个可以用于IntStream和LongStream的静态方法，帮助生成数值的范围： range和rangeClosed。
     * 这两个方法都是第一个参数接受起始值，第二个参数接受结束值。但 range是不包含结束值的，而rangeClosed则包含结束值
     */
    public void streams() {
        IntStream eventStream = IntStream.range(1, 100).filter(m -> m % 2 == 0);
        IntStream.rangeClosed(1, 100);
        System.out.println(eventStream.count());
    }

    /**
     * 可以使用静态方法Stream.of，通过显式值创建一个流。它可以接受任意数量的参数。 以下代码直接使用Stream.of创建了一个字符串流。
     * 然后，你可以将字符串转换为大写，再一个个打印出来
     */
    public void ofOfStream() {
        Stream<String> stringStream = Stream.of("java", "stream", "lambda");
        stringStream.map(s -> s.toUpperCase()).forEach(System.out::println);
    }

    public void ofStreamByArray() {
        int[] numbers = {2, 3, 5, 7, 11, 13};
        IntStream sum = Arrays.stream(numbers);
    }

}
