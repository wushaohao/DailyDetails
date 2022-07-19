package route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author:wuhao
 * @description:随机算法
 * @date:18/1/13
 */
public class Random {

    public static String getServer() {
        // 重建一个Map 避免服务器上下线导致的并发问题
        Map<String, Integer> serverMap = new HashMap<>();
        serverMap.putAll(IpMap.serverWeightMap);

        // 取得Ip地址
        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList = new ArrayList<>();
        keyList.addAll(keySet);

        java.util.Random random = new java.util.Random();
        int randomIndex = random.nextInt(keyList.size());

        return keyList.get(randomIndex);
    }
}
