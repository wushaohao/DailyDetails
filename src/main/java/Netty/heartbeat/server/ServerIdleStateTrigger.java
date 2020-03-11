package Netty.heartbeat.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author:wuhao
 * @description:断连触发器
 * @date:2020/3/11
 */
public class ServerIdleStateTrigger extends ChannelInboundHandlerAdapter {
  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if (evt instanceof IdleStateEvent) {
      IdleState state = ((IdleStateEvent) evt).state();
      if (state == IdleState.READER_IDLE) {
        // 在规定时间内没有收到客户端的上行数据, 主动断开连接
        ctx.disconnect();
      }
    } else {
      super.userEventTriggered(ctx, evt);
    }
  }
}
