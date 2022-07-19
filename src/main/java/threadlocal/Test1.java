package threadlocal;

/**
 * @author:wuhao
 * @description:
 * @date:18/5/10
 */
public class Test1 {

    public void setRegionId() {
        Demo3.setRegionId("791");
        try {
            new Test2().getRegionId();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Test1().setRegionId();
    }

}
