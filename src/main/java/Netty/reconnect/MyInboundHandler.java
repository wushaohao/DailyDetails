package Netty.reconnect;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoop;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author wuhao
 */
public class MyInboundHandler extends SimpleChannelInboundHandler {

    private Client client;

    public MyInboundHandler(Client client) {

        this.client = client;

    }

    @Override

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        final EventLoop eventLoop = ctx.channel().eventLoop();

        eventLoop.schedule(new Runnable() {

            @Override

            public void run() {

                client.createBootstrap(new Bootstrap(), eventLoop);

            }

        }, 1L, TimeUnit.SECONDS);

        super.channelInactive(ctx);

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }
}
