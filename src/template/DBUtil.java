package template;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    public static Connection getConnection() throws Exception{
        Connection conn;
        // 加载mysql驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 连接数据库的路径
        String url= "jdbc:mysql://127.0.0.1/open";
        // 用户名
        String userName="root";
        // 密码
        String pwd="123456";

        System.out.println("开始连接数据库...");

        conn= DriverManager.getConnection(url,userName,pwd);

        return conn;
    }

}
