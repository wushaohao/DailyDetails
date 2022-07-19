package dbpool;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

/**
 * @author:wuhao
 * @description:初始化线程连接池
 * @date:18/5/4
 */
public class SimpleConnectionPool {
    private static String url;

    private static String userName;

    private static String pwd;

    private static String className;

    private static String path = "simpleDb.properties";

    static Properties properties = new Properties();

    private static LinkedList<Connection> pool = new LinkedList<>();

    // 加载配置文件并注册驱动
    static {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        if (null != in) {
            try {
                properties.load(in);
                className = properties.getProperty("className");
                url = properties.getProperty("url");
                userName = properties.getProperty("userName");
                pwd = properties.getProperty("password");
                Class.forName(className);

                // 创建10个连接对象
                for (int i = 0; i < 10; i++) {
                    Connection conn = DriverManager.getConnection(url, userName, pwd);
                    pool.add(conn);
                }
                //
                System.out.println("初始化线程池完成..");

                //打印所创建的连接对象
                int i = 0;
                for (Connection conn : pool) {
                    System.out.println(conn + "..." + i++);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            try {
                throw new Exception("没有加载到配置文件");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取连接对象,由于可能同时会有多个对象来取，所以使用同步
     */
    public synchronized static Connection getConnection() {
        System.out.println("获取之前的连接如下..");

        int i = 0;

        for (Connection conn : pool) {
            System.out.println(conn + "..." + i++);
        }

        //从队列中移出一个连接对象，返回给调用者 防止为0的时候，导致异常，所以要进行判断
        if (pool.size() > 0) {
            Connection conn = pool.remove();
            return conn;
        } else {
            throw new RuntimeException("对不起，服务器忙！");
        }

    }

    /**
     * 将连接对象释放到队列中
     */
    public static void release(Connection connection) {
        pool.addLast(connection);
    }

}
