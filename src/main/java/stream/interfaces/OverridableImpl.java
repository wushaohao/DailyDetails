package stream.interfaces;

/**
 * @author:wuhao
 * @description:
 * @date:18/12/2
 */
public class OverridableImpl implements Defaulable {
    @Override
    public String notRequired() {
        return "Overridden implementation";
    }
}
