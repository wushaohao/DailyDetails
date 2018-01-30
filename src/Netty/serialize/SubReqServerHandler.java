package Netty.serialize;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 *
 * @author wuhao
 * @date 17/6/29
 */
public class SubReqServerHandler extends ChannelHandlerAdapter{

    public void channelRead(ChannelHandlerContext ctx,Object msg){
        SubscribeReq req= (SubscribeReq) msg;

        if ("BadGuy".equals(req.getUserName())){
            System.out.println("Service accept client subscript req:["+req.toString()+"]");
            ctx.writeAndFlush(resp(req.getSubReqId()));
        }
    }

    private SubscribeResp resp(int subReqId){
        SubscribeResp resp=new SubscribeResp();
        resp.setSubReqId(subReqId);
        // 成功
        resp.setRespCode(0);
        resp.setDesc("Netty book order succeed,3 days later,sent to your designated address!");
        return resp;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable){
        throwable.printStackTrace();
        ctx.close();
    }

}
