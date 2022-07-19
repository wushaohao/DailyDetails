package reference.phantomreference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author:wuhao
 * @description:虚引用测试
 * @date:18/8/7
 */
public class PhantomReferenceTest {
    private static class Connection {
        private int resourceId;

        public Connection(int resourceId) {
            System.out.println("分配资源：" + resourceId);
            this.resourceId = resourceId;
        }

        public int getResourceId() {
            return resourceId;
        }
    }

    private static class ConnectionMonitor extends PhantomReference<Connection> {
        private int resourceId;
        private ReferenceQueue<Connection> queue;

        public ConnectionMonitor(Connection referent, ReferenceQueue<Connection> queue) {
            super(referent, queue);
            this.resourceId = referent.getResourceId();
            this.queue = queue;
        }

        public void waitToCleanUP() {
            try {
                queue.remove();
                this.clear();
                //资源清理
                System.out.println("清理资源:" + this.resourceId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 上个连接的资源回收之后，才允许分配下一个连接
     *
     * @author Grucee
     */
    public static class ConnectioinManager {
        private static ConnectionMonitor monitor = null;

        private ConnectioinManager() {
        }

        public synchronized static Connection openConnection(int resourceId) {
            if (monitor != null) {
                monitor.waitToCleanUP();
            }

            Connection conn = new Connection(resourceId);
            ReferenceQueue<Connection> queue = new ReferenceQueue<Connection>();
            monitor = new ConnectionMonitor(conn, queue);
            return conn;
        }
    }

    public static void main(String[] args) {
        Connection conn = ConnectioinManager.openConnection(101);
        //如果不添加这一行，会发现一直得不到102资源
        conn = null;
        System.gc();
        conn = ConnectioinManager.openConnection(102);
    }
}
