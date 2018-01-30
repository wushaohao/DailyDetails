package Netty.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;

/**
 * @author wuhao
 */
public class NettyClientHandler extends ChannelHandlerAdapter {
    private ByteBuf firstMessage;

	/**
	 * 发送消息给服务端
	 * @param ctx
	 * @throws Exception
	 */
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		byte[] data = "服务器，给我一个APPLE".getBytes();
		firstMessage= Unpooled.buffer();
		firstMessage.writeBytes(data);
		ctx.writeAndFlush(firstMessage);
	}

	/**
	 * 客户端接受服务端数据
	 * @param ctx
	 * @param msg
	 * @throws Exception
	 */
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf buf = (ByteBuf) msg;
	    String rev = getMessage(buf);
	    System.out.println("客户端收到服务器数据:" + rev);
	}

	/**
	 * 获取信息
	 * @param buf
	 * @return
	 */
	private String getMessage(ByteBuf buf) {
		byte[] con = new byte[buf.readableBytes()];
		buf.readBytes(con);
		try {
			return new String(con, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}