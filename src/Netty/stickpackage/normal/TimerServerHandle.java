package Netty.stickpackage.normal;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 *
 * @author wuhao
 * @date 17/6/19
 */
public class TimerServerHandle extends ChannelHandlerAdapter {

    private int counter;

    public void channelRead(ChannelHandlerContext context,Object msg) throws Exception{
        ByteBuf buf= (ByteBuf) msg;
        byte[] req=new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body=new String(req,"UTF-8").substring(0,req.length-System.getProperty("line.separator").length());

        System.out.println("the time server receive order: "+ body+",the counter is :"+ ++counter);

        String currentTime="QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        currentTime=currentTime+System.getProperty("line.separator");
        ByteBuf resp= Unpooled.copiedBuffer(currentTime.getBytes());
        context.writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        ctx.close();
    }

}
