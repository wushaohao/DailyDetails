package designModels.Factory.demo;

import java.util.Properties;

/**
 * Created by wuhao on 17/6/6.
 */
public class MysqlConnectionImpl implements Connection{

    public MysqlConnectionImpl(Properties info) {
        System.out.println("MysqlConnectionImpl has been created...");
    }

    @Override
    public void prepareStatement(String sql) {
        System.out.println("return mysqlPrepareStatement with sql "+sql);
    }
}
