package Netty.reconnect;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author wuhao
 */
public class Client {

    private EventLoopGroup loop = new NioEventLoopGroup();

    public static void main(String[] args) {

        new Client().run();

    }

    public Bootstrap createBootstrap(Bootstrap bootstrap, EventLoopGroup eventLoop) {

        if (bootstrap != null) {

            final MyInboundHandler handler = new MyInboundHandler(this);

            bootstrap.group(eventLoop).
                    channel(NioSocketChannel.class).
                    option(ChannelOption.SO_KEEPALIVE, true).
                    handler(new ChannelInitializer<SocketChannel>() {

                        @Override

                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                            socketChannel.pipeline().addLast(handler);

                        }

                    });

            bootstrap.remoteAddress("localhost", 8888);

            bootstrap.connect().addListener(new ConnectionListener(this));

        }

        return bootstrap;

    }

    public void run() {

        createBootstrap(new Bootstrap(), loop);

    }

}
