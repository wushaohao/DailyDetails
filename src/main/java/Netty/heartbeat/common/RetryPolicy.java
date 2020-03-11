package Netty.heartbeat.common;

/**
 * @author:wuhao
 * @description:重试策略接口
 * @date:2020/3/11
 */
public interface RetryPolicy {
  boolean allowRetry(int retryCount);

  long getSleepTimeMs(int retryCount);
}
