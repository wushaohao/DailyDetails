package Netty.callback;

/**
 * @author:wuhao
 * @description:
 * @date:18/7/12
 */
public interface FetchCallBack {
    void onData(Data data) throws Exception;

    void errorData(Throwable throwable);

}
