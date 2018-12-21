package RateLimit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * @author:wuhao
 * @description:SmoothWarmingUp
 * @date:18/12/19
 */
public class RateLimiterGuava2 {
    public static void main(String[] args) {
        /**
         * SmoothWarmingUp创建方式：RateLimiter.create(double permitsPerSecond, long warmupPeriod, TimeUnit unit)
         * permitsPerSecond表示每秒新增的令牌数，warmupPeriod表示在从冷启动速率过渡到平均速率的时间间隔。
         */
        RateLimiter rateLimiter = RateLimiter.create(5, 1000, TimeUnit.MILLISECONDS);
        for (int i = 0; i < 5; i++) {
            System.out.println(rateLimiter.acquire());
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(rateLimiter.acquire());
        }
    }
}
