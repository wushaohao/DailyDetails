package Netty.timeout;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author wuhao
 * @date 17/5/18
 */
public class HeartBeatServer {

    static final int PORT = 8082;

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();


        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 100).handler(new LoggingHandler(LogLevel.INFO)).childHandler(new HeartbeatHandlerInitializer());

        // start the server
        ChannelFuture channelFuture = null;
        try {
            channelFuture = b.bind(PORT).sync();

            // wait until the server socket is close
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // shutdown all event loops to terminate all threads
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }
}
