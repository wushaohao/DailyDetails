package Netty.packagesolve;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author:wuhao
 * @description:客户端自定义处理handler类
 * @date:2020/3/13
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * 接受消息计数器
     */
  private int i;

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    super.channelActive(ctx);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    i++;
    ByteBuf buf = (ByteBuf) msg;
    System.out.println(msg);
    System.out.println("===========[" + i + "]");
    ByteBuf buf1 = Unpooled.copiedBuffer(buf);
    System.out.println("客户端获取到的是:" + buf.toString());
    String resMsg =
        Unpooled.copiedBuffer(String.valueOf(i + System.getProperty("line.separstor")).getBytes()).toString();
    ctx.writeAndFlush(buf1);
  }
}
