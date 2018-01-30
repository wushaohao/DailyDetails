package Netty.stickpackage.DelimiterBasedFrameDecode_demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by wuhao on 17/6/23.
 */
public class EchoServerHandler extends ChannelHandlerAdapter {
    private int counter;

    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
        String body= (String) msg;
        System.out.println("This is "+ ++counter +"times receive client:["+body+"]" );
        body+="$_";
        ByteBuf echo= Unpooled.copiedBuffer(body.getBytes());
        ctx.writeAndFlush(echo);
    }

    public void exceptionCaught(ChannelHandlerContext ctx,Throwable throwable){
        throwable.getStackTrace();
        ctx.close();// 发生异常关闭链路
    }
}
