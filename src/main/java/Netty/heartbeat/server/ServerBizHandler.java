package Netty.heartbeat.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author:wuhao
 * @description:服务器端的业务处理器
 * @date:2020/3/11
 */
public class ServerBizHandler extends SimpleChannelInboundHandler<String> {

  private final String REC_HEART_BEAT = "I had received the heart beat!";

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s)
      throws Exception {
    System.out.println("receive data: " + s);
  }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Established connection with the remote client.");
        // do something
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Disconnected with the remote client.");
        // do something
        ctx.fireChannelInactive();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
