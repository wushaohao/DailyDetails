package Netty.lesson;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

import java.io.UnsupportedEncodingException;

/**
 * @author:wuhao
 * @description:客户端事件处理类
 * @date:18/7/29
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    private ByteBuf message;

    /**
     * 发送消息给服务端
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端发送数据...");
        byte[] data = "Apple".getBytes();
        // 复制一个发送消息大小相等的缓冲区
        message = Unpooled.copiedBuffer(data);
        message.writeBytes(data);
        ctx.writeAndFlush(message);
    }

    /**
     * 客户端接受服务端数据
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf data = (ByteBuf) msg;
        String rev = getMessage(data);
        System.out.println("客户端收到服务器数据:" + rev);
    }

    /**
     * 获取信息
     *
     * @param buf
     * @return
     */
    private String getMessage(ByteBuf buf) {
        // 新建一个可读字节长度的字节数组
        byte[] con = new byte[buf.readableBytes()];
        // 将新建的字节数据读入缓冲区
        buf.readBytes(con);
        try {
            return new String(con, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
