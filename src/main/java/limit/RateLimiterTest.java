package limit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * @author:wuhao
 * @description:RateLimiter限流器
 * @date:2020/3/1
 */
public class RateLimiterTest {
    public static void main(String[] args) {
        /**
         * 创建一个限流器，设置每秒放置的令牌数是2个,速率是每秒可以2个消息
         * 返回的RateLimiter对象可以保证1秒内不会超过2个令牌，并且是固定速率的放置,达到平滑输出的效果
         */
        RateLimiter limiter = RateLimiter.create(2);

        while (true) {
            /**
             * acquire()获取一个令牌，并且返回这个获取这个令牌所需要的时间。如果桶里没有令牌则等待，直到有令牌。
             * acquire(N)可以获取多个令牌。
             */
//      System.out.println(limiter.acquire());

//      System.out.println(limiter.acquire(2));
//      System.out.println(limiter.acquire(1));
//      System.out.println(limiter.acquire(1));
//      System.out.println(limiter.acquire(1));

            System.out.println(limiter.acquire(1));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(limiter.acquire(1));
            System.out.println(limiter.acquire(1));
            System.out.println(limiter.acquire(1));
        }
    }

    private void smooth() {
        /**
         * 创建一个限流器，设置每秒放置的令牌数：2个。速率是每秒可以210的消息。
         * 返回的RateLimiter对象可以保证1秒内不会给超过2个令牌，并且是固定速率的放置。达到平滑输出的效果
         * 设置缓冲时间为3秒
         */
        RateLimiter limiter = RateLimiter.create(2, 3, TimeUnit.SECONDS);

        while (true) {
            /**
             * acquire()获取一个令牌，并且返回这个获取这个令牌所需要的时间。如果桶里没有令牌则等待，直到有令牌。
             * acquire(N)可以获取多个令牌。
             */
            System.out.println(limiter.acquire(1));
            System.out.println(limiter.acquire(1));
            System.out.println(limiter.acquire(1));
            System.out.println(limiter.acquire(1));
        }
    }
}

