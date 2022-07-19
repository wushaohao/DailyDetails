package Netty.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;

/**
 * @author wuhao
 */
public class NettyServerHandler extends ChannelHandlerAdapter {

    /**
     * 读取客户端传来的信息
     *
     * @param ctx
     * @param msg
     */
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        String received = getMessage(buf);
        System.out.println("服务器接收到消息：" + received);

        try {
            /**
             * 返回信息给客户端
             * flush():请求通过这个ChannelOutboundInvoker刷新所有挂起的消息
             * pipeline():返回指定ChannelPipeline
             */
            ctx.writeAndFlush(getSendByteBuf("APPLE"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从ByteBuf中获取信息 使用UTF-8编码返回
     */
    private String getMessage(ByteBuf buf) {
        // readableBytes():返回可读的字节数 writableBytes():返回可以写入的字节数
        byte[] con = new byte[buf.readableBytes()];
        // 将此缓冲区的数据传输到新创建的缓冲区
        buf.readBytes(con);
        try {
            return new String(con, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ByteBuf getSendByteBuf(String message)
            throws UnsupportedEncodingException {

        byte[] req = message.getBytes("UTF-8");
        /**
         * buffer:创建一个具有相当小的初始容量的新的大型Java堆缓冲区，它可以根据需求无限制地扩展其容量
         * copiedBuffer(ByteBuf buffer):创建一个新的缓冲区，其内容是指定缓冲区可读字节的副本
         */

        ByteBuf pingMessage = Unpooled.buffer();
        // writeBytes(byte[] src):将指定的源数组的数据(src)传输到从当前writerIndex开始的缓冲区，并通过传输的字节数增加writerIndex
        pingMessage.writeBytes(req);
        return pingMessage;
    }
}