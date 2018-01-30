package threadlocal;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author:wuhao
 * @Description:ThreadLocal连接创建
 * @Date:17/10/20
 */
public class ConnectionManager {

    /**
     * volatile 防止指令重排序
     */
    private static volatile Connection INSTANCE = null;

    /**
     * 锁对象
     */
    private static final Object LOCKER = new Object();

    /**
     * 双重检查机制
     * @return
     */
    private Connection getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (LOCKER) {
                if (INSTANCE == null) {
                    INSTANCE = getConnection();
                }
            }
        }
        return INSTANCE;
    }

    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>() {
        @Override
        protected Connection initialValue() {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/test", "root", "Abcd123"
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return conn;
        }
    };


    private static Connection getConnection() {
        return connectionHolder.get();
    }

    private static void setConnection(Connection conn) {
        connectionHolder.set(conn);
    }

}
