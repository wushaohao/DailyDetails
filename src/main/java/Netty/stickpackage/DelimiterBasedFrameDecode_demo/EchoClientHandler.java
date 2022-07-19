package Netty.stickpackage.DelimiterBasedFrameDecode_demo;

import NIO.Channel;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by wuhao on 17/6/24.
 */
public class EchoClientHandler extends ChannelHandlerAdapter {
    private int counter;

    private String ECHO_REQ = "HI,GUY this is test,welcome to Netty.$_";

    public void ChannelActive(ChannelHandlerContext ctx) {
        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));

        }
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("this is " + ++counter + " times receive server:[" + msg + "]");
    }

    public void ChannelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable) {
        throwable.printStackTrace();
        ctx.channel();
    }

}
