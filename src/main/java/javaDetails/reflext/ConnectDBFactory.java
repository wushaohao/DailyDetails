package javaDetails.reflext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author:wuhao
 * @description:数据库连接工厂类
 * @date:2019/4/15
 */
public class ConnectDBFactory {
    public static Connection getDBConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/sm";
            String userName = "root";
            String pwd = "123456";
            conn = DriverManager.getConnection(url, userName, pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
