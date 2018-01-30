package route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author:wuhao
 * @description:轮询算法
 * @date:18/1/13
 */
public class RoundRobin {

    private static Integer pos = 0;

    public static String getServer(){
        // 重建一个Map 避免服务器上下线导致的并发问题
        Map<String, Integer> serverMap = new HashMap<>();
        serverMap.putAll(IpMap.serverWeightMap);

        // 取得Ip地址
        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList = new ArrayList<>();
        keyList.addAll(keySet);

        String server = null;
        synchronized (pos){
            if (pos > keySet.size()) {
                pos = 0;
            }
            server = keyList.get(pos);
            pos++;
        }

        return server;
    }
}
