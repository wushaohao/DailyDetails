package Netty.lesson;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author:wuhao
 * @description:服务端
 * @date:18/7/29
 */
public class Server {

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        //1.绑定2个线程组 用来处理客户端通道的access和读写
        bootstrap.group(bossGroup, workGroup).
                //2.绑定服务端通道NioServerSocketChannel
                        channel(NioServerSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true).
                //3.给读写事件的通道绑定handler去真正处理读写
                        childHandler(new MySocketChannelHandler());

        try {
            //4.监听端口
            ChannelFuture future = bootstrap.bind(8888).sync();
            if (future.isSuccess()) {
                System.out.println("服务端启动成功....");
            }
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }
}
