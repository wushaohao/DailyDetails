package rxjava;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

import java.util.Arrays;
import java.util.List;

/**
 * @author:wuhao
 * @Description:操作符Filter
 * @Date:18/1/6
 */
public class Filter {

    public static void main(String[] args) {
        Integer[] number = {1,2,3,4,5,6,7};
        List<Integer> lists = Arrays.asList(number);
        // create 创建被观察者
        Observable<Integer> integerObservable = Observable.from(lists);

        Subscriber mySubscriber = RxJavaCommon.getSubscriber();

        /**
         * 改变流 Filter:Filter运算符会过滤被观察者，被观察者发射的数据中只有通过你在谓词函数中指定的测试后才能继续往下流动
         * Func<T, R>表示一个单参数的函数，T是第一个参数的类型，R是返回结果的类型。
         */
        integerObservable.filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer % 2 == 0;
            }
        }).subscribe(mySubscriber);
    }
}
