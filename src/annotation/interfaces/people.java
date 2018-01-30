package annotation.interfaces;

/**
 *
 * @author wuhao
 * @date 17/3/12
 */
public interface people {
    String getName();

    int getAge();

    /**
     * @Deprecated
     * 标注此方法已经过时 不需要实现该接口的类都使用
     * @return
     */
    @Deprecated
    String address();
}
