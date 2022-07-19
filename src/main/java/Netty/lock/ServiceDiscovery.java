package Netty.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author:wuhao
 * @description:服务发现
 * @date:2019/12/19
 */
@Slf4j
public class ServiceDiscovery {
    private CountDownLatch latch;

    private volatile List<String> serviceAddressList = new ArrayList<>();

    private String registryAddress;

    public ServiceDiscovery(String registryAddress) {
        this.registryAddress = registryAddress;
        ZooKeeper zk = connectServer();
        if (zk != null) {
            watchNode(zk);
        }
    }

    /**
     * 通过服务发现，获取服务提供方的地址
     *
     * @return
     */
    public String discover() {
        String data = null;
        int size = serviceAddressList.size();
        if (size > 0) {
            if (size == 1) {
                data = serviceAddressList.get(0);
                log.info("unique service address : {}", data);
            } else {
                data = serviceAddressList.get(ThreadLocalRandom.current().nextInt(size));
                log.info("choose an address:{}", data);
            }
        }
        return data;
    }

    /**
     * 连接ZooKeeper
     *
     * @return
     */
    private ZooKeeper connectServer() {
        ZooKeeper zk = null;
        try {
            zk =
                    new ZooKeeper(
                            registryAddress,
                            10000,
                            new Watcher() {
                                @Override
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
     * 监听zk
     *
     * @param zk
     */
    private void watchNode(ZooKeeper zk) {
        try {
            List<String> nodeList =
                    zk.getChildren(
                            "",
                            new Watcher() {
                                @Override
                                public void process(WatchedEvent watchedEvent) {
                                    if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                                        watchNode(zk);
                                    }
                                }
                            });
            List<String> dataList = new ArrayList<>();
            nodeList.forEach(
                    item -> {
                        try {
                            byte[] bytes = zk.getData("" + "/" + item, false, null);
                            dataList.add(new String(bytes));
                        } catch (KeeperException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
            log.debug("node data: {}", dataList);
            this.serviceAddressList = dataList;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

