package rxjava;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

import java.util.Arrays;
import java.util.List;

/**
 * @author:wuhao
 * @Description:操作符Map
 * @Date:18/1/6
 */
public class Map {

    public static void main(String[] args) {
        Integer[] number = {1, 2, 3, 4, 5, 6, 7};
        List<Integer> lists = Arrays.asList(number);
        // create 创建被观察者
        Observable<Integer> integerObservable = Observable.from(lists);

        Subscriber mySubscriber = RxJavaCommon.getSubscriber();

        /**
         * Map:Map运算符将会将你指定的函数应用到被观察者发射的每一项，并返回一个被观察者，这个被观察者发射的数据就是你指定函数的返回结果
         *
         */
        integerObservable.map(new Func1<Integer, Object>() {
            @Override
            public Object call(Integer integer) {
                return integer * integer;
            }
        }).subscribe(mySubscriber);

        /**
         *
         */
        Observable.just("a", "b", "c")
                //使用map进行转换，参数1：转换前的类型，参数2：转换后的类型
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        String name = s;
                        System.out.println("tag:map--1--" + s);
                        return s;
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("tag:map--2--" + s);
            }
        });
    }
}
