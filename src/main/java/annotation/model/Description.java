package annotation.model;

import java.lang.annotation.*;

/**
 * Created by wuhao on 17/3/12.
 * 它是使用@interface关键字定义的一个注解。
 * 然后我们看下面的几个方法，String desc();虽然它很类似于接口里面的方法，
 * 其实它在注解里面只是一个成员变量（成员以无参无异常的方式声明），int age() default 18;（成员变量可以用default指定一个默认值的）。
 * 最后我们要知道：
 * ①.成员类型是受限制的，合法的类型包括基本的数据类型以及String，Class，Annotation,Enumeration等。
 * ②.如果注解只有一个成员，则成员名必须取名为value()，在使用时可以忽略成员名和赋值号（=）。
 * ③.注解类可以没有成员，没有成员的注解称为标识注解。
 *
 * @Target是这个注解的作用域，ElementType.METHOD是这个注解的作用域的列表，METHOD是方法声明，除此之外，还有： CONSTRUCTOR（构造方法声明）,FIELD（字段声明）,LOCAL VARIABLE（局部变量声明）,METHOD（方法声明）,PACKAGE（包声明）,PARAMETER（参数声明）,TYPE（类接口）
 * 第二行：@Retention是它的生命周期，前面不是说注解按照运行机制有一个分类嘛，RUNTIME就是在运行时存在，可以通过反射读取。除此之外，还有:
 * SOURCE（只在源码显示，编译时丢弃）,CLASS（编译时记录到class中，运行时忽略）,RUNTIME（运行时存在，可以通过反射读取）
 * 第三行：@Inherited是一个标识性的元注解，它允许子注解继承它。
 * 第四行：@Documented，生成javadoc时会包含注解。
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Description {
    String desc();

    String author();

    int age() default 18;
}
