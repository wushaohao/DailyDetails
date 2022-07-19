package Netty.channel;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.channel.socket.oio.OioDatagramChannel;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;

/**
 * @author:wuhao
 * @description:通道属性demo
 * @date:18/7/15
 */
public class OptionsExample {
    public static void main(String[] args) {
        // 创建属性对象
        final AttributeKey<Integer> id = AttributeKey.valueOf("id");
        // 客户端引导对象
        Bootstrap bootstrap = new Bootstrap();
        // 设置EventLoop 设置通道类型
        bootstrap.group(new NioEventLoopGroup()).channel(NioSocketChannel.class)
                //设置ChannelHandler
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {

                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                        System.out.println("Receive Data");
                        byteBuf.clear();
                    }

                    @Override
                    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
                        // 通道注册后执行 获取属性值
                        Integer iValue = ctx.channel().attr(id).get();
                        System.out.println("属性值是:" + iValue);
                    }
                });

        // 设置通道选项，再痛到注册后或被创建后设置
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true).option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
        // 设置通道属性
        bootstrap.attr(id, 123456);
        ChannelFuture future = bootstrap.connect("www.baidu.com", 80);
        future.syncUninterruptibly();
    }


    public static void udpProtocol() {
        Bootstrap b = new Bootstrap();
        b.group(new OioEventLoopGroup()).channel(OioDatagramChannel.class).handler(new SimpleChannelInboundHandler<ByteBuf>() {
            @Override
            protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                // do Nothing
            }
        });

        final ChannelFuture f = b.bind(new InetSocketAddress(0));
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    System.out.println("Channel bound");
                } else {
                    System.out.println("Channel attemp failed");
                    channelFuture.cause().printStackTrace();
                }
            }
        });

    }
}
