package Netty.reconnect;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class ConnectionListener implements ChannelFutureListener {

    private Client client;

    public ConnectionListener(Client client) {

        this.client = client;

    }

    @Override

    public void operationComplete(ChannelFuture channelFuture) throws Exception {

        if (!channelFuture.isSuccess()) {

            System.out.println("Reconnect");

            final EventLoop loop = channelFuture.channel().eventLoop();

            loop.schedule(new Runnable() {

                @Override

                public void run() {

                    client.createBootstrap(new Bootstrap(), loop);

                }

            }, 1L, TimeUnit.SECONDS);

        }

    }

}
