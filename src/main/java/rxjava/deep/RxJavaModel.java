package rxjava.deep;


import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

/**
 * @author:wuhao
 * @description:RxJava线程模型
 * @date:2019/12/19
 */
@Slf4j
public class RxJavaModel {
    public static void main(String[] args) {
        Observable.just("aaa", "bbb")
                .observeOn(Schedulers.newThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return s.toUpperCase();
                    }
                })
                .subscribeOn(Schedulers.single())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        System.out.println(s);
                    }
                });

        /**
         *
         */
        Observable.just("Hello World")
                // subscribeOn通过接收一个Scheduler参数，来指定对数据的处理运行在特定的线程调度器Scheduler上,若多次执行subscribeOn，则只有一次起作用
                .subscribeOn(Schedulers.single())
                .map(
                        new Function<String, String>() {
                            @Override
                            public String apply(String s) throws Exception {
                                s.toUpperCase();
                                log.info("map1", s);
                                return s;
                            }
                        })
                //observeOn同样接收一个Scheduler参数，用来指定下游操作运行在特定的线程调度器Scheduler上,若多次执行observeOn，则每次均起作用，线程会一直切换
                .observeOn(Schedulers.io())
                .map(
                        new Function<String, String>() {
                            @Override
                            public String apply(String s) throws Exception {
                                s = s + " tony.";
                                log.info("map2", s);
                                return s;
                            }
                        })
                .subscribeOn(Schedulers.computation())
                .map(
                        new Function<String, String>() {
                            @Override
                            public String apply(String s) throws Exception {
                                s = s + "it is a test.";
                                log.info("map3", s);
                                return s;
                            }
                        })
                .observeOn(Schedulers.newThread())
                .subscribe(
                        new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                log.info("subscribe", s);
                                System.out.println(s);
                            }
                        });
    }


}
