package multilock;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import java.util.concurrent.TimeUnit;

/**
 * @author:wuhao
 * @description:Redis实现分布式锁
 * @date:2018/12/21
 */
@Slf4j
public class RedisKeyLock {
    private final static long ACCQUIRE_LOCK_TIMEOUT_IN_MS = 10 * 1000;

    /**
     * 锁失效时间
     */
    private final static int EXPIRE_IN_SECOND = 5;

    private final static long WAIT_INTERVAL_IN_MS = 100;

    private static RedisKeyLock lock;

    private JedisPool jedisPool;

    public RedisKeyLock(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public static RedisKeyLock getInstance(JedisPool jedisPool) {
        if (lock == null) {
            lock = new RedisKeyLock(jedisPool);
        }
        return lock;
    }

    public void lock(String redisKey) {
        Jedis resource = null;
        try {
            long now = System.currentTimeMillis();
            resource = jedisPool.getResource();

            long timeoutAt = now + ACCQUIRE_LOCK_TIMEOUT_IN_MS;
            boolean flag = false;
            while (true) {
                String expireAt = String.valueOf(now + EXPIRE_IN_SECOND * 1000);
                long ret = resource.setnx(redisKey, expireAt);

                // 获取到锁
                if (ret == 1) {
                    flag = true;
                    break;
                } else {
                    //未获取锁，重试获取锁
                    String oldExpireAt = resource.get(redisKey);
                    if (oldExpireAt != null && Long.parseLong(oldExpireAt) < now) {
                        oldExpireAt = resource.getSet(redisKey, expireAt);
                        if (Long.parseLong(oldExpireAt) < now) {
                            flag = true;
                            break;
                        }
                    }
                }
                if (timeoutAt < now) {
                    break;
                }
                try {
                    TimeUnit.NANOSECONDS.sleep(WAIT_INTERVAL_IN_MS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            if (!flag) {
                throw new RuntimeException("cannot acquire lock now...");
            }
        } finally {
            if (resource != null) {
                jedisPool.returnResource(resource);
            }
        }

    }

    public boolean unlock(String redisKey) {
        Jedis resource = null;
        try {
            resource = jedisPool.getResource();
            resource.del(redisKey);
            return true;
        } catch (JedisException je) {
            je.printStackTrace();
            if (resource != null) {
                jedisPool.returnResource(resource);
            }
            return false;
        } catch (Exception e) {
            log.error("lock", e);
            return false;
        } finally {
            if (resource != null) {
                jedisPool.returnResource(resource);
            }
        }

    }
}
