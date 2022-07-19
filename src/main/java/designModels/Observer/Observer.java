package designModels.Observer;

/**
 * Created by wuhao on 17/5/27.
 */
public interface Observer {
    void dataUpdate(Observable observable);

    String getName();
}
