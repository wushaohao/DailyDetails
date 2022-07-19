package stream.interfaces;

/**
 * @author:wuhao
 * @description:java8默认接口
 * @date:18/12/2
 */
public interface Defaulable {
    default String notRequired() {
        return "Default implementation";
    }

//    String abstractMethod();
}
