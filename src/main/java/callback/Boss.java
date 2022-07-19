package callback;

/**
 * @author wuhao
 * @date 17/2/5
 */
public class Boss implements Work {
    @Override
    public void callmeOk() {
        System.out.println("完成工作后 打电话给我!");
    }
}
