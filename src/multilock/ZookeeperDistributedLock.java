package multilock;

import com.google.common.base.Joiner;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;

/**
 * @author:wuhao
 * @description:zk分布式事务锁
 * @date:18/7/4
 */
public class ZookeeperDistributedLock {
    public final static Joiner j = Joiner.on("|").useForNull("");
    /**
     *zk客户端
     */
    private ZooKeeper zooKeeper;

    /**
     * zk节点根目录
     */
    private String root = "/locks";

    /**
     * 锁名称
     */
    private String lockName = null;

    /**
     * 当前线程创建的序列node
     */
    private ThreadLocal<String> nodeId = new ThreadLocal<>();

    /**
     * 用来同步等待zk client链接到了服务端
     */
    private CountDownLatch connectedSingal = new CountDownLatch(1);

    private static int sessionTimeOut = 3000;

    private final static byte[] data = new byte[0];

    /**
     * ZookeeperDistributedLock的构造函数创建zkclient，并且注册了监听事件，然后调用connectedSignal.await()挂起当前线程。当
     * zk client链接到服务器后，会给监听器发送SyncConnected事件，监听器判断当前链接已经建立了，则调用 connectedSignal.countDown();激活当前线程，然后创建root节点
     * @param config
     * @param lockName
     */
    public ZookeeperDistributedLock(String config,String lockName) {
        this.lockName = lockName;
        try {
            zooKeeper=new ZooKeeper(config, sessionTimeOut, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        connectedSingal.countDown();
                    }
                }
            });
            connectedSingal.await();
            Stat stat = zooKeeper.exists(root, false);
            if (null == stat) {
                //创建根节点
                zooKeeper.create(root, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    /**
     * 锁监听器
     */
    class LockWatcher implements Watcher {
        private CountDownLatch latch = null;

        public LockWatcher(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void process(WatchedEvent event) {
            if (event.getType()==Event.EventType.NodeDeleted) {
                latch.countDown();
            }
        }
    }

    /**
     * 获取锁的方法lock，内部首先创建/root/lockName的顺序临时节点，然后获取/root下所有的孩子节点，并对子节点进行排序，然后判断自己是不是最小的编号，如果是直接返回true标示获取锁成功。
     * 否者看比自己小一个号的节点是否存在，存在则注册该节点的事件，然后挂起当前线程，等待比自己小一个数的节点释放锁后发送节点删除事件，事件里面激活当前线程
     */
    public void lock() {
        try {
            String myNode = zooKeeper.create(root + "/" + lockName, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(j.join(Thread.currentThread().getName() , myNode + "created"));
            // 取出所有的子节点
            List<String> subNodes = zooKeeper.getChildren(root, false);
            TreeSet<String> sortedNodes = new TreeSet<>();
            for (String node:subNodes) {
                sortedNodes.add(root + "/" +node);
            }
            // 最小节点
            String smallNode = sortedNodes.first();
            String preNode = sortedNodes.lower(myNode);
            if (myNode.equals(smallNode)) {
                // 如果是最小节点，则表示获取到锁
                System.out.println(j.join(Thread.currentThread().getName(), myNode, "get lock"));
                this.nodeId.set(myNode);
                return;
            }
            CountDownLatch latch = new CountDownLatch(1);
            // 注册监听
            Stat stat = zooKeeper.exists(preNode, new LockWatcher(latch));
            if (stat != null) {
                System.out.println(j.join(Thread.currentThread().getName(), myNode, "waiting for" + root + "/" + preNode + "release lock"));
                // 一直等待其他线程获取锁(?)
                latch.await();
                nodeId.set(myNode);
                latch = null;
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unlock() {
        System.out.println(Thread.currentThread().getName()+nodeId+"unlock");
        if (null != nodeId) {
            try {
                zooKeeper.delete(nodeId.get(), -1);
                nodeId.remove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (KeeperException e) {
                e.printStackTrace();
            }
        }
    }
}
