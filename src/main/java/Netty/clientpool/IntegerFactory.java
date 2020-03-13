package Netty.clientpool;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author:wuhao
 * @description:生成消息的唯一序列号
 * @date:2020/3/12
 */
public class IntegerFactory {
    private static class SingletonHolder {
        private static final AtomicInteger INSTANCE = new AtomicInteger();
    }

    private IntegerFactory(){}

    public static final AtomicInteger getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
