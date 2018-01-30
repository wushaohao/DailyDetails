package designModels.Singleton;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author:wuhao
 * @Description:CAS实现单例模式
 * @Date:17/9/18
 */
public class Singleton {
    private static final AtomicReference<Singleton>  INSTANCE = new AtomicReference<>();

    private Singleton(){}

    public static Singleton getInstance(){
        for (;;) {
            Singleton singleton = INSTANCE.get();
            if (null == singleton){
                return singleton;
            }

            singleton = new Singleton();
            if (INSTANCE.compareAndSet(null,singleton)){
                return singleton;
            }
        }
    }
}
