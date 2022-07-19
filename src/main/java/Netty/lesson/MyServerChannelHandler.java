package Netty.lesson;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;


/**
 * @author:wuhao
 * @description:服务端时间处理handler类
 * @date:18/7/29
 */
public class MyServerChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf bufs = (ByteBuf) msg;
        String receives = getMessage(bufs);
        System.out.println("服务器接收到消息：" + receives);
        ctx.writeAndFlush(getSendByteBuf("Apple"));
    }

    private Object getSendByteBuf(String apple) {
        try {
            byte[] req = apple.getBytes("UTF-8");
            ByteBuf pingMessage = Unpooled.buffer();
            pingMessage.writeBytes(req);
            return pingMessage;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getMessage(ByteBuf bufs) {
        byte[] con = new byte[bufs.readableBytes()];
        bufs.writeBytes(con);
        try {
            return new String(con, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
