package rxjava;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * @author:wuhao
 * @Description:去抖(限流)
 * @Date:18/1/11
 */
public class ThrottleWithTimeout {

    public static void main(String[] args) {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    Thread.sleep(1000);
                    int i = 0;
                    subscriber.onNext(i);
                    // next实现
                    subscriber.onCompleted();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.newThread()).throttleWithTimeout(100, TimeUnit.MILLISECONDS)
        .subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Completed");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("=="+integer);
            }
        });
    }
}
