package Netty.callback;


/**
 * @author:wuhao
 * @description:
 * @date:18/7/12
 */
public class Worker {

    public void doWork() {
        IFetcher fetch= new MyFetcher(new Data(1,2));

        fetch.fetchData(new FetchCallBack() {
            @Override
            public void onData(Data data) throws Exception {
                System.out.println("Data receive...");
            }

            @Override
            public void errorData(Throwable throwable) {
                System.out.println("error Data...");
            }
        });
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.doWork();
    }
}
