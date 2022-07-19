package java8;

import java.util.Optional;

/**
 * @author:wuhao
 * @description:Optional测试类
 * @date:18/11/2
 */
public class OptionalDemo {
    public static void main(String[] args) {
        Integer integer1 = null;
        Integer integer2 = 10;

        // Optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> optional = Optional.ofNullable(integer1);
        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        Optional<Integer> optional2 = Optional.of(integer2);

        System.out.println(sum(optional, optional2));

    }

    public static Integer sum(Optional<Integer> a, Optional<Integer> b) {
        // Optional.isPresent - 判断值是否存在
        System.out.println("第一个参数的值是否存在:" + a.isPresent());
        System.out.println("第二个参数的值是否存在:" + b.isPresent());

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value = a.orElse(0);

        // Optional.get - 获取值，值需要存在
        Integer value2 = b.get();
        return value + value2;
    }
}
