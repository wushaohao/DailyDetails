package route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author:wuhao
 * @description:源地址哈希
 * @date:18/1/13
 */
public class HashRoute {
    public static String getServer() {
        // 重建一个Map 避免服务器上下线导致的并发问题
        Map<String, Integer> serverMap = new HashMap<>();
        serverMap.putAll(IpMap.serverWeightMap);

        // 取得Ip地址
        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList = new ArrayList<>();
        keyList.addAll(keySet);

        // 在web应用中可通过 HttpServlet的getRemoteIp()来获取
        String remoteIp = "127.0.0.1";
        int hashCode = remoteIp.hashCode();

        int serverListSize = keyList.size();
        int serverPos = hashCode % serverListSize;

        return keyList.get(serverPos);
    }
}
