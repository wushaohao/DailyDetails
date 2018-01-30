package designModels.Factory.demo;

import java.util.Properties;

/**
 *
 * @author wuhao
 * @date 17/6/6
 */
public interface Driver {
    Connection connect(Properties connectFiles);
}
