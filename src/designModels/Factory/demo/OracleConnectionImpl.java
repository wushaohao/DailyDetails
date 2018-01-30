package designModels.Factory.demo;

import java.util.Properties;

/**
 *
 * @author wuhao
 * @date 17/6/6
 */
public class OracleConnectionImpl implements Connection {

    public OracleConnectionImpl(Properties connections) {
        System.out.println("OracleConnectionImpl has been created...");
    }

    @Override
    public void prepareStatement(String sql) {
        System.out.println("return oracle created sql "+sql);
    }
}
