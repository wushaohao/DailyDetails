package Netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 *
 * @author wuhao
 * @date 17/3/26
 *
 * EventLoopGroup 管理的线程数可以通过构造函数进行设置，如果没有设置
 * 那么默认取 -Dio.netty.eventLoopThreads,如果该系统参数也没有设置，则为可用CPU内核数*2
 */
public class NettyServer {
     public void run() throws Exception{
         // configure the server 安装创建Netty服务端
         EventLoopGroup bossGroup=new NioEventLoopGroup();
         EventLoopGroup workGroup=new NioEventLoopGroup();

//         ServerBootstrap b=new ServerBootstrap();
//         b.group(bossGroup,workGroup).channel(NioServerSocketChannel.class)
//                 .option(ChannelOption.SO_BACKLOG,100).handler(new LoggingHandler(LogLevel.INFO))
//                 .childHandler(new ChannelInitializer<SocketChannel>() {
//                     @Override
//                     protected void initChannel(SocketChannel socketChannel) throws Exception {
//                         socketChannel.pipeline().addLast(
//                                 new EchoServerHandler());
//                     }
//                 });
//         // Start the server
//         ChannelFuture f=b.bind(port).sync();
     }
}
