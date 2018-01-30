package rxjava;


import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func0;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author:wuhao
 * @Description:RxJava
 * @Date:18/1/6
 */
public class RxJava1 {
    public static void main(String[] args) {
        // 创建发射源-被观察者
        Observable<String> sender = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("create():Hi RxJava");
                // create方法 创建的被观察者需要调用onCompleted才会触发接受者的onCompleted
                subscriber.onCompleted();
            }
        });

        /**
         * just:just()将为你创建一个Observable并自动为你调用onNext( )发射数据
         */

        Observable justObserver = Observable.just("just():Hi RxJava");

        /**
         * from:from(T)，遍历集合，发送每个item
         */
        List<String> list = new ArrayList<>();
        list.add("from1");
        list.add("from2");
        list.add("from3");
        Observable fromObservable = Observable.from(list);

        /**
         * defer:使用defer(Fun())，有观察者订阅时才创建Observable，并且为每个观察者创建一个新的Observable
         */
        Observable<String> deferObservable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just("defer:Hi RxJava");
            }
        });

        /**
         * interval():创建一个按固定时间间隔发射整数序列的Observable，可用作定时器
         */
        //每隔一秒发送一次
        Observable intervalObservable = Observable.interval(1, TimeUnit.SECONDS);

        /**
         * range( ),创建一个发射特定整数序列的Observable，第一个参数为起始值，第二个为发送的个数，如果为0则不发送，负数则抛异常
         */
        //将发送整数10，11，12，13，14
        Observable angeObservable = Observable.range(10, 5);

        /**
         * 使用timer( ),创建一个Observable，它在一个给定的延迟后发射一个特殊的值，等同于Android中Handler的postDelay( )方法
         */
        //3秒后发射一个值
        Observable timeObservable = Observable.timer(3, TimeUnit.SECONDS);

        /**
         * 使用repeat( ),创建一个重复发射特定数据的Observable
         */
        //重复发射3次
        Observable repeatObservable = Observable.just("repeatObservable").repeat(3);

        // 创建接收者-观察者
        Observer<String> receiver = new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };

        sender.subscribe(receiver);

    }
}
