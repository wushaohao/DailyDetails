package Netty.heartbeat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author:wuhao
 * @description:客户端心跳
 * @date:2020/3/4
 */
public class ClientIdleStateTrigger extends ChannelInboundHandlerAdapter {
  public static final String HEART_BEAT = "heart beat";

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if (evt instanceof IdleStateEvent) {
      IdleState state = ((IdleStateEvent) evt).state();
      if (state == IdleState.WRITER_IDLE) {
        // 写出到服务端
        ctx.writeAndFlush(HEART_BEAT);
      }
    } else {
      super.userEventTriggered(ctx, evt);
    }
  }
}
