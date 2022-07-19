package route;

import java.util.*;

/**
 * @author:wuhao
 * @description:加权轮询算法
 * @date:18/1/13
 */
public class WeightRoundRobin {

    private static Integer pos = 0;

    public static String getServer() {
        // 重建一个Map 避免服务器上下线导致的并发问题
        Map<String, Integer> serverMap = new HashMap<>();
        serverMap.putAll(IpMap.serverWeightMap);

        // 取得Ip地址
        Set<String> keySet = serverMap.keySet();
        Iterator<String> iterator = keySet.iterator();

        List<String> serverList = new ArrayList<>();
        while (iterator.hasNext()) {
            String server = iterator.next();
            int weight = serverMap.get(server);
            for (int i = 0; i < weight; i++) {
                serverList.add(server);
            }
        }

        String server = null;
        synchronized (pos) {
            if (pos > serverList.size()) {
                pos = 0;
            }

            server = serverList.get(pos);
        }
        return server;
    }
}
