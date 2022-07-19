package stream.optional;

import java.util.Optional;

/**
 * @author:wuhao
 * @description:Optional
 * @date:18/12/3
 */
public class OptionalDemo {
    public static void main(String[] args) {
        /**
         * 如果Optional实例持有一个非空值，则isPresent()方法返回true，否则返回false；orElseGet()方法，Optional实例持有null，
         * 则可以接受一个lambda表达式生成的默认值；map()方法可以将现有的Opetional实例的值转换成新的值；
         * orElse()方法与orElseGet()方法类似，但是在持有null的时候返回传入的默认值
         */
        Optional<String> fullName = Optional.ofNullable(null);
        System.out.println("Full Name is Set?" + fullName.isPresent());
        System.out.println("Full Name:" + fullName.orElseGet(() -> "[none]"));
        System.out.println(fullName.map(s -> "Hey" + s + "!").orElse("Hey stranger"));

        Optional<String> firstName = Optional.of("Tom");
        System.out.println("First Name is set?" + firstName.isPresent());
        System.out.println("First Name:" + firstName.orElseGet(() -> "[none]"));
        System.out.println(firstName.map(s -> "Hey" + s + "!").orElse("Hey stranger!"));
    }
}
