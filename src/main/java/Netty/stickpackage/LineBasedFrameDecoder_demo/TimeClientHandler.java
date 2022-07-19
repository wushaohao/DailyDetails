package Netty.stickpackage.LineBasedFrameDecoder_demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;

/**
 * @author wuhao
 * @date 17/6/20
 */
public class TimeClientHandler extends ChannelHandlerAdapter {
    private int counter;

    private byte[] req;
    private Logger log = Logger.getLogger(TimeClientHandler.class.getName());

    public TimeClientHandler() {
        System.out.println("QUERY TIME ORDER" + System.getProperty("line.separator").getBytes());
    }

    public void channelActive(ChannelHandlerContext ctx) {
        ByteBuf message = null;
        for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String body = (String) msg;
        System.out.println("Now is:" + body + "; the counter is" + ++counter);
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable) {
        ctx.close();
    }
}
