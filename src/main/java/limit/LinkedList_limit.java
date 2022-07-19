package limit;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * @author:wuhao
 * @description:限流实现方式-计数器
 * @date:2020/3/1
 */
@Slf4j
public class LinkedList_limit {
    /**
     * 服务访问次数,可以放在Redis中,实现分布式系统的访问计数
     */
    private long counter = 0L;
    /**
     * 使用LinkedList来记录滑动窗口的10个格子
     */
    LinkedList<Long> longs = new LinkedList<>();

    private void doCheck() throws InterruptedException {
        while (true) {
            longs.add(counter);
            if (longs.size() > 10) {
                longs.removeFirst();
            }
            // 比较最后一个和第一个，两者相差一秒
            if ((longs.peekLast() - longs.peekFirst()) > 100) {
                // TODO-Limit
                log.info("********限流开始*******");
            }
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) {
        LinkedList_limit limit = new LinkedList_limit();
        try {
            limit.doCheck();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
