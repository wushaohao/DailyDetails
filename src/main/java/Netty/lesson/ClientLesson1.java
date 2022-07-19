package Netty.lesson;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author:wuhao
 * @description:客户端请求
 * @date:18/7/29
 */
public class ClientLesson1 {

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel s) throws Exception {
                        s.pipeline().addLast(new StringDecoder());
                        s.pipeline().addLast(new StringEncoder());
                        s.pipeline().addLast(new ClientHandler());
                    }
                });

        ChannelFuture future = null;
        try {
            future = bootstrap.connect("127.0.0.1", 8888);
            //
            Boolean rtm = future.awaitUninterruptibly(3000);

            if (future.isSuccess() && rtm) {
                System.out.println("---------connect server success---------");
                ChannelFuture f = future.channel().writeAndFlush("Apple");
                f.addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        System.out.println("request is ok");
                    }
                });
            }

            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
