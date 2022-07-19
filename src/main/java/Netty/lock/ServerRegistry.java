package Netty.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author:wuhao
 * @description:zk注册
 * @date:2019/12/19
 */
@Slf4j
public class ServerRegistry {

    private CountDownLatch latch;

    private String registryAddress;

    public ServerRegistry(String registryAddress) {
        this.registryAddress = registryAddress;
    }

    public void register(String data) {
        if (data != null) {
            ZooKeeper zk = connectServer();
            if (zk != null) {
                createNode(zk, data);
            }
        }
    }

    private ZooKeeper connectServer() {
        ZooKeeper zk = null;

        try {
            zk =
                    new ZooKeeper(
                            registryAddress,
                            1000,
                            new Watcher() {
                                public void process(WatchedEvent watchedEvent) {
                                    if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                                        latch.countDown();
                                    }
                                }
                            });
            latch.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return zk;
    }

    /**
     * 创建ZK临时顺序节点
     *
     * @param zk
     * @param data
     */
    private void createNode(ZooKeeper zk, String data) {
        byte[] bytes = data.getBytes();
        try {
            String path =
                    zk.create("", bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            log.info("create zookeeper node({}=>{})", path, data);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


