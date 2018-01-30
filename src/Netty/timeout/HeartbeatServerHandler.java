package Netty.timeout;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 *
 * @author wuhao
 * @date 17/5/18
 */
public class HeartbeatServerHandler  extends ChannelInboundHandlerAdapter{

    /**
     * 定义了心跳时，要发送的内容
     */
    private static final ByteBuf HEARTBEAT_SEQUENCE= Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("HeartBeat", CharsetUtil.UTF_8));

    public void userEventTriggred(ChannelHandlerContext ctx,Object evt) throws Exception{
        // 判断是否是 IdleStateEvent 事件，是则处理
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event= (IdleStateEvent) evt;
            String type="";
            if (event.state()== IdleState.READER_IDLE){
                type="read idel";
            }else if(event.state()== IdleState.WRITER_IDLE){
                type="write idel";
            }else if (event.state()== IdleState.ALL_IDLE){
                type="all idel";
            }
            // 将心跳内容发送给客户端
            ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate()).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);

            System.out.println(ctx.channel().remoteAddress()+"超时类型:"+type);
        }else{
            super.userEventTriggered(ctx,evt);
        }

    }

}
