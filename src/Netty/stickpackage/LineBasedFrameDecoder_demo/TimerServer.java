package Netty.stickpackage.LineBasedFrameDecoder_demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 *
 * @author wuhao
 * @date 17/6/20
 */
public class TimerServer {

    public void bind(int port){
        // 创建服务端NIO 线程组
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workGroup=new NioEventLoopGroup();

        //
        ServerBootstrap bootstrap=new ServerBootstrap();

        bootstrap.group(bossGroup,workGroup).channel(NioServerSocketChannel.class).
                option(ChannelOption.SO_BACKLOG,1024).childHandler(new ChildChannelHandler());

        try {
            // 绑定端口
            ChannelFuture f=bootstrap.bind(port).sync();
            // 等待服务端监听关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
            socketChannel.pipeline().addLast(new StringDecoder());
            socketChannel.pipeline().addLast(new TimeServerHandler());
        }
    }

    public static void main(String[] args) {
        int port=8099;
        if (args!=null && args.length>0){
            port=Integer.valueOf(args[0]);
        }
        new TimerServer().bind(port);
    }
}
