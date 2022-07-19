package threadlocal;

/**
 * @author:wuhao
 * @description:
 * @date:18/5/10
 */
public class Test2 {

    public String getRegionId() throws Exception {
        String regionId = Demo3.getRegionId();
        System.out.println("regionId:" + regionId);
        return regionId;
    }
}
