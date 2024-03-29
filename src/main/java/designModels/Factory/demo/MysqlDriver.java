package designModels.Factory.demo;

import java.util.Properties;

/**
 * @author wuhao
 * @date 17/6/6
 */
public class MysqlDriver implements Driver {
    @Override
    public Connection connect(Properties connectFiles) {
        return new MysqlConnectionImpl(connectFiles);
    }
}
