package route;

import java.util.*;
import java.util.Random;

/**
 * @author:wuhao
 * @description:加权随机算法
 * @date:18/1/13
 */
public class WeightRound {

    public static String getServer(){
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

        Random random = new Random();
        int serverPos = random.nextInt(serverList.size());

        return serverList.get(serverPos);
    }
}
