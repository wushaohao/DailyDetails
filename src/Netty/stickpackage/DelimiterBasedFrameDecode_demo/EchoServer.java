package Netty.stickpackage.DelimiterBasedFrameDecode_demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by wuhao on 17/6/23.
 */
public class EchoServer {
    public void bind(int port) throws Exception {
        //
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workGroup=new NioEventLoopGroup();

        try{
            ServerBootstrap bootstrap=new ServerBootstrap();

            bootstrap.group(bossGroup,workGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG,1024)
                    .handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ByteBuf delimiter= Unpooled.copiedBuffer("$_".getBytes());
                    socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
                    socketChannel.pipeline().addLast(new StringDecoder());
                    socketChannel.pipeline().addLast(new EchoServerHandler());
                }
            });


            //绑定端口 同等待成功
            ChannelFuture f=bootstrap.bind(port).sync();
            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        }finally {
            // 优雅的关闭 释放线程池
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        int port=8090;
        if (args!=null && args.length>0){
            port=Integer.valueOf(args[0]);
        }
        new EchoServer().bind(port);
    }
}
