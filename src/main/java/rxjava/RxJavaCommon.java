package rxjava;

import rx.Subscriber;

/**
 * @author:wuhao
 * @Description:创建Subscribr获取公共类
 * @Date:18/1/6
 */
public class RxJavaCommon {

    public static Subscriber getSubscriber() {
        // Subscriber 特殊类型观察者 它可以取消订阅被观察者
        Subscriber<Integer> mySubscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("RxJava completed");
            }

            @Override
            public void onError(Throwable throwable) {
                // 处理错误情况逻辑
                System.out.println();
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("RxJava onNext " + integer);
            }
        };

        return mySubscriber;
    }
}
