package Netty.clientpool;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author:wuhao
 * @description:用于接收服务端返回的消息，并且根据消息序列号获取对应的callback程序
 * @date:2020/3/12
 */
public class SocketClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();

        ByteBuf responseBuf = (ByteBuf)msg;
        responseBuf.markReaderIndex();

        int length = responseBuf.readInt();
        int seq = responseBuf.readInt();

        responseBuf.resetReaderIndex();

        //获取消息对应的callback
        SocketClient.CallbackService callbackService = ChannelUtils.<SocketClient.CallbackService>removeCallback(channel, seq);
        callbackService.receiveMessage(responseBuf);
    }
}
