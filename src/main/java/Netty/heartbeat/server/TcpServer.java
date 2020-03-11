package Netty.heartbeat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author:wuhao
 * @description:服务器端
 * @date:2020/3/11
 */
public class TcpServer {

  private int port;
  private ServerHandlerInitializer serverHandlerInitializer;

  public TcpServer(int port) {
    this.port = port;
    this.serverHandlerInitializer = new ServerHandlerInitializer();
  }

  public void start() {
    EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    EventLoopGroup workerGroup = new NioEventLoopGroup();

    ServerBootstrap bootstrap = new ServerBootstrap();
    bootstrap
        .group(bossGroup, workerGroup)
        .channel(NioServerSocketChannel.class)
        .childHandler(this.serverHandlerInitializer);

    try {
      ChannelFuture future = bootstrap.bind(port).sync();
      System.out.println("Server start listen at " + port);
      future.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }

  public static void main(String[] args) {
    int port = 2222;
    new TcpServer(port).start();
  }
}
