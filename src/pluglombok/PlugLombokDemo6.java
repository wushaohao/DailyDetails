package pluglombok;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;

/**
 * @author:wuhao
 * @description:插件lombok@RequiredArgsConstructor
 * @date:18/11/1
 */
@RequiredArgsConstructor
public class PlugLombokDemo6 {
    /**
     * 一个class可以有很多属性，但你可能只关心其中的几个字段，那么可以使用@RequiredArgsConstructor。@NonNull将标注这个字段不应为null，
     * 初始化的时候会检查是否为空，否则抛出NullPointException。
     * 在上面的无参构造函数中被忽略了。那么，对于关注的字段标注@NonNull, @RequiredArgsConstructor则会生成带有这些字段的构造器
     */
    @NonNull
    private int id;
    private Date date;
}
