package pluglombok;

import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author:wuhao
 * @description:插件lombok@NoArgsConstructor
 * @date:18/11/1
 */
@NoArgsConstructor
public class PlugLombokDemo7 {
    /**
     * 当你想要创建一个valueobject，DDD中的值对象，要求实现Immutable，那么无参数构造器就不合适了。@NoArgsConstructor会生成一个空的构造器。
     * 如果你设置了final field，那么编译会报错。
     * 如果你强制执行创建无参数构造器。即，@NoArgsConstructor(force = true)，那么final的field会初始化为0/false/null。通常适合与@Data集成
     */
    @NonNull
    private int id;
}
