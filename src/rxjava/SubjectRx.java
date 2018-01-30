package rxjava;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;
import java.util.concurrent.TimeUnit;

/**
 * @author:wuhao
 * @Description:Subject--既可以作为发送发也可以作为接收方的特殊类
 * @Date:18/1/7
 */
public class SubjectRx {

    /**
     * Observer会接收AsyncSubject的```onComplete()``之前的最后一个数据，如果因异常而终止，AsyncSubject将不会释放任何数据，但是会向Observer传递一个异常通知
     */
    public static void asyncSubjectTest(){
        AsyncSubject<String> asyncSubject = AsyncSubject.create();
        asyncSubject.onNext("AsyncSubject Test1");
        asyncSubject.onNext("AsyncSubject Test2");
        asyncSubject.onNext("AsyncSubject Test3");
        asyncSubject.onCompleted();

        asyncSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("AsyncSubject completed!");
                System.out.println("***********************");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }

    /**
     * Observer会接收到BehaviorSubject被订阅之前的最后一个数据，再接收其他发射过来的数据，如果BehaviorSubject被订阅之前没有发送任何数据，则会发送一个默认数据
     */
    public static void behaviorSubjectTest(){
        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create("default");
//        behaviorSubject.onNext("BehaviorSubject Test1");
//        behaviorSubject.onNext("BehaviorSubject Test2");

        behaviorSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("BehaviorSubject completed");
                System.out.println("***********************");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("BehaviorSubject:" + s);
            }
        });
        behaviorSubject.onNext("BehaviorSubject Test3");
        behaviorSubject.onNext("BehaviorSubject Test4");
        behaviorSubject.onCompleted();
    }

    /**
     * PublishSubject比较容易理解，相对比其他Subject常用，它的Observer只会接收到PublishSubject被订阅之后发送的数据
     */
    public static void publishSubjectTest(){
        PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject.onNext("PublishSubject pre1");
        publishSubject.onNext("PublishSubject pre2");

        publishSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("PublishSubject completed");
                System.out.println("***********************");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("PublishSubject:" + s);
            }
        });

        publishSubject.onNext("PublishSubject after1");
        publishSubject.onNext("PublishSubject after2");
        publishSubject.onCompleted();

    }

    /**
     * ReplaySubject会发射所有数据给观察者，无论它们是何时订阅的
     * ReplaySubject，在重放缓存增长到一定大小的时候或过了一段时间后会丢弃旧的数据
     */
    public static void replaySubjectTest(){
        //创建默认初始缓存容量大小为16的ReplaySubject，当数据条目超过16会重新分配内存空间，使用这种方式，不论ReplaySubject何时被订阅，Observer都能接收到数据
//        ReplaySubject<String> replaySubject = ReplaySubject.create();
        //创建指定初始缓存容量大小为100的ReplaySubject
//        ReplaySubject<String> replaySubject = ReplaySubject.create(100);
        //只缓存订阅前最后发送的2条数据
//        ReplaySubject replaySubject = ReplaySubject.createWithSize(2);
        ////replaySubject被订阅前的前1秒内发送的数据才能被接收
        ReplaySubject replaySubject = ReplaySubject.createWithTime(1, TimeUnit.SECONDS, Schedulers.computation());

        replaySubject.onNext("ReplaySubject pre1");
        replaySubject.onNext("ReplaySubject pre2");

        replaySubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("ReplaySubject completed");
                System.out.println("***********************");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("ReplaySubject:"+s);
            }
        });

        replaySubject.onNext("ReplaySubject after1");
        replaySubject.onNext("ReplaySubject after2");
        replaySubject.onCompleted();
    }


    /**
     * 即作为被观察者 又作为观察者
     * 把Subject作为Observer传入subscribe()，但接收数据还是要通过Observer来接收，借用Subject来连接Observable和Observer
     */
    public static void bridgeTest(){
        ReplaySubject<String> replaySubject = ReplaySubject.create();
        // 作为Observer接收数据
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Bridge Test1");
                subscriber.onNext("Bridge Test2");
                subscriber.onCompleted();
            }
        }).subscribe(replaySubject);
        // 作为Observable
        replaySubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Bridge completed");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("Bridge Test:"+s);
            }
        });
    }

    public static void main(String[] args) {
        asyncSubjectTest();
        behaviorSubjectTest();
        publishSubjectTest();
        replaySubjectTest();
        bridgeTest();
    }
}
