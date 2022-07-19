package Netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.log4j.Logger;


/**
 * @author wuhao
 */
public class NettyServerBootstrap {

    private static Logger logger = Logger.getLogger(NettyServerBootstrap.class);

    private int port;

    public NettyServerBootstrap(int port) {
        this.port = port;
        bind();
    }

    private void bind() {

        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try {

            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(boss, worker).
                    channel(NioServerSocketChannel.class).
                    //连接数
                            option(ChannelOption.SO_BACKLOG, 1024).
                    //不延迟，消息立即发送
                            option(ChannelOption.TCP_NODELAY, true).
                    //长连接
                            childOption(ChannelOption.SO_KEEPALIVE, true).
                    // 处理类
                            childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel)
                                throws Exception {
                            ChannelPipeline p = socketChannel.pipeline();
                            p.addLast(new NettyServerHandler());
                        }
                    });
            ChannelFuture f = bootstrap.bind(port).sync();
            if (f.isSuccess()) {
                logger.debug("启动Netty服务成功，端口号：" + this.port);
            }
            // 关闭连接
            f.channel().closeFuture().sync();

        } catch (Exception e) {
            logger.error("启动Netty服务异常，异常信息：" + e.getMessage());
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        NettyServerBootstrap server = new NettyServerBootstrap(9999);

    }

}