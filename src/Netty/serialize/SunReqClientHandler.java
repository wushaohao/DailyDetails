package Netty.serialize;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 *
 * @author wuhao
 * @date 17/6/29
 */
public class SunReqClientHandler extends ChannelHandlerAdapter {

    public SunReqClientHandler() {
    }

    public void channelActive(ChannelHandlerContext ctx){
        for (int i = 0; i < 10; i++) {
            ctx.write(subReq(i));
        }
        ctx.flush();
    }
    private SubscribeReq subReq(int i){
        SubscribeReq req=new SubscribeReq();
        req.setSubReqId(i);
        req.setAddress("南京市江宁区");
        req.setPhoneNum("130xxxx0989");
        req.setProductName("Netty 权威指南");
        req.setUserName("BadGuy");

        return req;
    }

    public void channelRead(ChannelHandlerContext ctx,Object msg){
        System.out.println("Receive server response:["+msg+"]");
    }

    public void channelReadComplete(ChannelHandlerContext ctx){
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable){
        throwable.printStackTrace();
        ctx.close();
    }
}
