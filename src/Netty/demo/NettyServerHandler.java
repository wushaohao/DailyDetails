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
	 * @param ctx
	 * @param msg
	 */
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
		
		ByteBuf buf = (ByteBuf) msg;
		
		String received = getMessage(buf);
		System.out.println("服务器接收到消息：" + received);
		
		try {
			// 返回信息给客户端
			ctx.writeAndFlush(getSendByteBuf("APPLE"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从ByteBuf中获取信息 使用UTF-8编码返回
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
	
	private ByteBuf getSendByteBuf(String message)
			throws UnsupportedEncodingException {

		byte[] req = message.getBytes("UTF-8");
		ByteBuf pingMessage = Unpooled.buffer();
		pingMessage.writeBytes(req);
		return pingMessage;
	}
}