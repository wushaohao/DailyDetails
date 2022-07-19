package Netty.lock;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author:wuhao
 * @description:Netty客户端实现服务注册
 * @date:2019/12/19
 */
@Slf4j
public class NettyLockClient {
    private String ip;
    private int port;

    private Bootstrap bootstrap = new Bootstrap();

    private SocketChannel socketChannel = new NioSocketChannel();

    public NettyLockClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
        try {
            start(ip, port);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void start(String host, int port) throws InterruptedException {
        EventLoopGroup workGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors() * 2);

        bootstrap
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .group(workGroup)
                .remoteAddress(host, port)
                .handler(
                        new ChannelInitializer<Channel>() {
                            @Override
                            protected void initChannel(Channel socketChannel) throws Exception {
                                socketChannel
                                        .pipeline()
                                        //IdleEvent 事件定时向服务端放送 Ping 消息以此来检测 SocketChannel 是否中断
                                        .addLast(new IdleStateHandler(20, 10, 0))
                                        .addLast(new StringEncoder())
                                        .addLast(new StringDecoder());
                            }
                        });
        doConnect(port, host);
    }

    /**
     * 建立连接，并且可以实现自动重连
     *
     * @param port
     * @param host
     * @throws InterruptedException
     */
    private void doConnect(int port, String host) throws InterruptedException {
        if (socketChannel != null && socketChannel.isActive()) {
            return;
        }

        final int portConnect = port;
        final String hostConnect = host;

        ChannelFuture future = bootstrap.connect(host, port);

        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture futureListener) throws Exception {
                if (futureListener.isSuccess()) {
                    socketChannel = (SocketChannel) futureListener.channel();
                    log.info("Connect to server successfully!");
                } else {
                    // 实现自动重连
                    log.info("Failed to connect to server, try connect after 10s");
                    futureListener.channel().eventLoop().schedule(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                doConnect(portConnect, hostConnect);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }, 10, TimeUnit.SECONDS);
                }
            }
        }).sync();
    }
}


