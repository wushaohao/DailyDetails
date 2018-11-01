package pluglombok;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author:wuhao
 * @description:插件lombok用法Getter&Setter
 * @date:18/10/31
 */
@Data
public class PlugLombokDemo1 {

    @Getter
    @Setter
    private int age;

    @Getter
    @Setter
    private boolean active;

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private Boolean none;

    @Getter
    @Setter
    private Date date;

    @Getter
    @Setter
    private String name;


    @Override
    public String toString() {
        return String.format("%s (age: %d)", name, age);
    }


    /**
     * Getter声明创建getter方法；
     * Setter声明创建setter方法；
     * @Setter(AccessLevel.PROTECTED)可以添加参数，指定权限为私有；
     * Attention！关于boolean的set前缀都是set，但getter不同，小写的boolean，即基本类型，前缀是is; Boolean，即包装类型，前缀是get；
     * @param args
     */
    public static void main(String[] args) {
        PlugLombokDemo1 plugLombokDemo1 = new PlugLombokDemo1();
        plugLombokDemo1.setActive(true);
        plugLombokDemo1.setAge(23);
        plugLombokDemo1.setDate(new Date());
        plugLombokDemo1.setName("Hulk");
        plugLombokDemo1.setNone(false);

        Date date = plugLombokDemo1.getDate();
        Boolean none = plugLombokDemo1.getNone();
        boolean active = plugLombokDemo1.isActive();

        System.out.println(date + "\t" + none + "\t" + active);
    }
}
