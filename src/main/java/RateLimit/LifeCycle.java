package RateLimit;

/**
 * @Author:wuhao
 * @Description:生命周期
 * @Date:17/9/13
 */
public interface LifeCycle {

    public void start();

    public boolean isStarted();

    public void stop();
}
