package stream.interfaces;

/**
 * @author:wuhao
 * @description:测试类
 * @date:18/12/3
 */
public class TestMain {
    public static void main(String[] args) {
        Defaulable defaulable = DefaulableFactory.create(DefaultableImpl::new);
        System.out.println(defaulable.notRequired());

        defaulable = DefaulableFactory.create(OverridableImpl::new);
        System.out.println(defaulable.notRequired());

    }
}
