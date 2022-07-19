package stream.interfaces;

import java.util.function.Supplier;

/**
 * @author:wuhao
 * @description:默认工厂接口
 * @date:18/12/3
 */
public class DefaulableFactory {
    static Defaulable create(Supplier<Defaulable> supplier) {
        return supplier.get();
    }
}
