package rxjava;


import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author:wuhao
 * @Description:FlatMap测试类
 * @Date:18/1/7
 */
public class FlatMap {

    public static void main(String[] args) {
        List<String> lists = new ArrayList<String>();
        lists.add("10");
        lists.add("20");
        lists.add("30");
        lists.add("40");
        lists.add("50");

        Observable.from(lists)
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        int delay = 300;
                        if (s.equals("30")) {
                            delay = 500;
                        }
                        return Observable.just(s).delay(delay, TimeUnit.MILLISECONDS);
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("flatMap:" + s);
            }
        });

        Observable.from(lists)
                .concatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        int delay = 300;
                        if (s.equals("30")) {
                            delay = 500;
                        }
                        return Observable.just(s).delay(delay, TimeUnit.MILLISECONDS);
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("concatMap:" + s);
            }
        });
    }
}
