package Netty.callback;

/**
 * @author:wuhao
 * @description:
 * @date:18/7/12
 */
public class MyFetcher implements IFetcher{
    final Data data;

    public MyFetcher(Data data) {
        this.data = data;
    }

    @Override
    public void fetchData(FetchCallBack fetchCallBack) {
        try {
            fetchCallBack.onData(data);
        } catch (Exception e) {
            fetchCallBack.errorData(e);
            e.printStackTrace();
        }
    }
}
