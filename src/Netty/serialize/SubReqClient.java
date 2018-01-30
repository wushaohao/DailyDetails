package Netty.serialize;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.apache.log4j.net.SocketServer;

/**
 *
 * @author wuhao
 * @date 17/6/29
 */
public class SubReqClient {
    public void connect(int port,String host) throws InterruptedException {
        //
        EventLoopGroup workGroup=new NioEventLoopGroup();

        try{
            Bootstrap b=new Bootstrap();
            b.group(workGroup).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ObjectDecoder(1024, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                            socketChannel.pipeline().addLast(new ObjectEncoder());
                            socketChannel.pipeline().addLast(new SunReqClientHandler());
                        }
                    });

            // 发起异步连接操作
            ChannelFuture f=b.connect(host,port).sync();
            // 等到客户端链路关闭
            f.channel().closeFuture().sync();

        }finally {
            // 优雅退出 释放NIO线程组
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port=8080;
        if (args!=null && args.length>0){
            port=Integer.parseInt(args[0]);
        }
        new SubReqClient().connect(port,"127.0.0.1");
    }
}
