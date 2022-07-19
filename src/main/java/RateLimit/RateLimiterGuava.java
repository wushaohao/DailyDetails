package RateLimit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author:wuhao
 * @description:Google-Guava限流器－平滑突发限流(SmoothBursty)
 * @date:18/12/19
 */
public class RateLimiterGuava {
    /**
     * 。
     *
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 1、RateLimiter.create(5) 表示桶容量为5且每秒新增5个令牌，即每隔200毫秒新增一个令牌；
         * 2、limiter.acquire()表示消费一个令牌，如果当前桶中有足够令牌则成功（返回值为0），如果桶中没有令牌则暂停一段时间，
         * 比如发令牌间隔是200毫秒，则等待200毫秒后再去消费令牌（如上测试用例返回的为0.198239，差不多等待了200毫秒桶中才有令牌可用），
         * 这种实现将突发请求速率平均为了固定请求速率
         */
        RateLimiter rateLimiter = RateLimiter.create(5);
//        System.out.println(rateLimiter.acquire());
//        System.out.println(rateLimiter.acquire());
//        System.out.println(rateLimiter.acquire());
//        System.out.println(rateLimiter.acquire());
//        System.out.println(rateLimiter.acquire());
//        System.out.println(rateLimiter.acquire());
        /**
         * limiter.acquire(5)表示桶的容量为5且每秒新增5个令牌，令牌桶算法允许一定程度的突发，所以可以一次性消费5个令牌，
         * 但接下来的limiter.acquire(1)将等待差不多1秒桶中才能有令牌，且接下来的请求也整形为固定速率了
         */
        System.out.println(rateLimiter.acquire(5));
        System.out.println(rateLimiter.acquire(1));
        System.out.println(rateLimiter.acquire(1));

        /**
         * 第一秒突发了10个请求，令牌桶算法也允许了这种突发（允许消费未来的令牌），但接下来的limiter.acquire(1)将等待差不多2秒桶中才能有令牌，且接下来的请求也整形为固定速率了
         */
        System.out.println(rateLimiter.acquire(10));
        System.out.println(rateLimiter.acquire(1));
        System.out.println(rateLimiter.acquire(1));
    }

    /**
     * 限流某个接口的时间窗请求数
     * 即一个时间窗口内的请求数，如想限制某个接口/服务每秒/每分钟/每天的请求数/调用量。
     * 如一些基础服务会被很多其他系统调用，比如商品详情页服务会调用基础商品服务调用，
     * 但是怕因为更新量比较大将基础服务打挂，这时我们要对每秒/每分钟的调用量进行限速
     */
    public void rateLimiter() {
        LoadingCache<Long, AtomicLong> counter = CacheBuilder.
                newBuilder().
                expireAfterWrite(2, TimeUnit.SECONDS).
                build(new CacheLoader<Long, AtomicLong>() {
                    @Override
                    public AtomicLong load(Long aLong) throws Exception {
                        return new AtomicLong(0);
                    }
                });

        long limit = 1000;

        while (true) {
            // 得到当前秒
            long currentSeconds = System.currentTimeMillis() / 1000;
            try {
                if (counter.get(currentSeconds).incrementAndGet() > limit) {
                    System.out.println("限流了:" + currentSeconds);
                    continue;
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            // 业务逻辑
        }
    }

}
