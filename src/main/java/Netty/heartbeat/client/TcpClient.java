package Netty.heartbeat.client;

import Netty.heartbeat.common.ExponentialBackOffRetry;
import Netty.heartbeat.common.RetryPolicy;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author:wuhao
 * @description:TCP连接客户端
 * @date:2020/3/11
 */
public class TcpClient {
  private String host;
  private int port;
  private Bootstrap bootstrap;

  /** 将channel保存起来, 可用于在其他非handler的地方发送数据 */
  private Channel channel;

  /** 重连策略 */
  private RetryPolicy retryPolicy;

  public TcpClient(String host, int port) {
    this(host, port, new ExponentialBackOffRetry(1000, Integer.MAX_VALUE, 60 * 1000));
  }

  public TcpClient(String host, int port, RetryPolicy retryPolicy) {
    this.host = host;
    this.port = port;
    init();
  }

  private void init() {
    EventLoopGroup group = new NioEventLoopGroup();
    // bootstrap可重用 只需要在TcpClient实例化的时候进行初始化
    bootstrap = new Bootstrap();
    bootstrap
        .group(group)
        .channel(NioSocketChannel.class)
        .handler(new ClientHandlersInitializer(TcpClient.this));
  }

  public void connect() {
    synchronized (bootstrap) {
      ChannelFuture future = bootstrap.connect(host, port);
      this.channel = future.channel();
    }
  }

  public RetryPolicy getRetryPolicy() {
    return retryPolicy;
  }

  public static void main(String[] args) {
    TcpClient tcpClient = new TcpClient("localhost", 2020);
    tcpClient.connect();
  }
}
