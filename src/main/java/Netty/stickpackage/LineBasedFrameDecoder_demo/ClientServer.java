package Netty.stickpackage.LineBasedFrameDecoder_demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author wuhao
 * @date 17/6/20
 */
public class ClientServer {

    public void connect(int port, String host) {
        // 配置客户端线程组
        EventLoopGroup workGroup = new NioEventLoopGroup();

        //
        Bootstrap b = new Bootstrap();
        b.group(workGroup).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                socketChannel.pipeline().addLast(new StringDecoder());
                socketChannel.pipeline().addLast(new TimeClientHandler());
            }
        });

        try {
            // 发起异步连接操作
            ChannelFuture f = b.connect(host, port).sync();
            // 等待客户端连路关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 8099;
        if (args != null && args.length > 0) {
            port = Integer.valueOf(args[0]);
        }
        new ClientServer().connect(port, "127.0.0.1");
    }
}
