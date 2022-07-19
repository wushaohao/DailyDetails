package designModels.Factory.demo;

import java.util.Properties;

/**
 * @author wuhao
 * @date 17/6/6
 */
public class test {

    public static void main(String[] args) {

        try {
            Class clazz = Class.forName("com.mysql.jdbc.mysqlDriver");//com.oracle.jdbc.OracleDriver
            Driver driver = (Driver) clazz.newInstance();
            Properties properties = new Properties();
            String sql = "select * from user_tables";

            properties.setProperty("url", "jdbc:mysql://localhost:3306");
            properties.setProperty("username", "csf");
            properties.setProperty("pwd", "Abcd123");

            Connection conn = driver.connect(properties);
            conn.prepareStatement(sql);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}

