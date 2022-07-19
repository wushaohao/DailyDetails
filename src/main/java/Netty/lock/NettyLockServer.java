package Netty.lock;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author:wuhao
 * @description:Netty服务端
 * @date:2019/12/19
 */
@Slf4j
public class NettyLockServer {
    private int port;

    public NettyLockServer(int port) {
        this.port = port;
    }

    public void runServer() {
        //
        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup =
                new NioEventLoopGroup(Runtime.getRuntime().availableProcessors() * 2);

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap
                .group(workGroup, boosGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .option(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(
                        new ChannelInitializer() {
                            @Override
                            protected void initChannel(Channel channel) throws Exception {
                                ChannelPipeline pipeline = channel.pipeline();
                                pipeline
                                        .addLast(new StringEncoder())
                                        .addLast(new StringDecoder());
//                    .addLast(new PushServerHandler());
                            }
                        });

        try {
            ChannelFuture future = bootstrap.bind(port).sync();
            if (future.isSuccess()) {
                log.info("server is start...");
            }

            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}

