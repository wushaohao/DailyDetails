package rxjava;


import org.apache.log4j.Logger;
import rx.Observable;
import rx.Subscriber;

import java.util.Arrays;
import java.util.List;

/**
 * @author:wuhao
 * @Description:RxJava 测试类
 * @Date:18/1/6
 */
public class RxJava2 {

    private static final Logger log = Logger.getLogger(RxJava2.class);

    public static void main(String[] args) {
        Integer[] number = {1, 2, 3, 4, 5, 6, 7};
        List<Integer> lists = Arrays.asList(number);
        // create 创建被观察者
        Observable<Integer> integerObservable = Observable.from(lists);

        Subscriber mySubscriber = RxJavaCommon.getSubscriber();

        // 将subscriber连接到被观察者
        integerObservable.subscribe(mySubscriber);
    }
}
