package google.guava.calculateruntime;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * @author:wuhao
 * @description:计算运行时间
 * @date:18/11/1
 */
public class StopwatchDemo {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        int count = 0;

        for (int i = 0; i < 10000; i++) {
            count++;
        }

        long nanos = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println("运行时常是:" + nanos + "\t" + count);
    }
}
