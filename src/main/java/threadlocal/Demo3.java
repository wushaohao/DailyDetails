package threadlocal;

import org.apache.commons.lang.StringUtils;

/**
 * @author:wuhao
 * @description:ThreadLocal测试类
 * @date:18/5/10
 */
public class Demo3 {
    private static ThreadLocal s_region = new ThreadLocal();


    public static void setRegionId(String regionId) {
        s_region.set(regionId);
    }

    public static String getRegionId() throws Exception {
        String regionId = (String) s_region.get();
        if (StringUtils.isBlank(regionId)) {
            throw new Exception("regionId 为空");
        }
        return regionId;
    }
}
