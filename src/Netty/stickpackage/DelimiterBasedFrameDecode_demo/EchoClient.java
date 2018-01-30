package Netty.stickpackage.DelimiterBasedFrameDecode_demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by wuhao on 17/6/24.
 */
public class EchoClient {

    public void connect(int port,String host) throws Exception{
        //配置客户端线程组
        EventLoopGroup workGroup=new NioEventLoopGroup();

        try{
            Bootstrap b=new Bootstrap();
            b.group(workGroup).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY,true).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ByteBuf delimeter= Unpooled.copiedBuffer("$_".getBytes());
                    socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimeter));
                    socketChannel.pipeline().addLast(new StringDecoder());
                    socketChannel.pipeline().addLast(new EchoClientHandler());
                }
            });

            // 发起异步连接操作
            ChannelFuture f=b.connect(host,port).sync();
            //等待客户端链路关闭
            f.channel().closeFuture().sync();
        }finally {
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        int port=8090;
        if (args!=null && args.length>0){
            port=Integer.valueOf(args[0]);
        }
        new EchoClient().connect(port,"127.0.0.1");
    }
}
