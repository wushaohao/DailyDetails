package interviews.test.observer;

/**
 * @author:wuhao
 * @description:测试客户端
 * @date:2022/8/8
 */
public class Client {
    public static void main(String[] args) {
        AObserver aObserver = new AObserver();
        ATestObserver testA = new ATestObserver();
        aObserver.addObserver(testA);
        System.out.println("------测试observer model-----");
        aObserver.callObserver();

        int[] arrays = new int[]{4, 5, 6, 7, 0, 1, 2};


    }
}
