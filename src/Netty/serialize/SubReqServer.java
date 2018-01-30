package Netty.serialize;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 *
 * @author wuhao
 * @date 17/6/29
 */
public class SubReqServer {

    public void bind(int port) throws Exception{
        //
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workGroup=new NioEventLoopGroup();

        try{
            ServerBootstrap bootstrap=new ServerBootstrap();
            bootstrap.group(bossGroup,workGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG,1024)
                    .handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new ObjectDecoder(1024*1024, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                    socketChannel.pipeline().addLast(new ObjectEncoder());
                    socketChannel.pipeline().addLast(new SubReqServerHandler());
                }
            });

            ChannelFuture f= bootstrap.bind(port).sync();

            f.channel().closeFuture().sync();

        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port=8080;
        if (args!=null && args.length>0){
            port=Integer.parseInt(args[0]);
        }
        new SubReqServer().bind(port);
    }
}
