package Netty.rpc.server;

import Netty.rpc.impl.HelloServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author:wuhao
 * @description:handler业务处理器
 * @date:2022/9/15
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private final String AGREEMENT = "HelloService#hello#";

    /**
     * 客户端连接进行数据处理
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务端Handler收到客户端消息:{" + msg + "}");

        //客户端在调用服务器的api时 需要遵守相应的协议
        // 协议:每次发的消息必须HelloService#hello#开头
        if (msg.toString().startsWith(AGREEMENT)) {
            String response = new HelloServiceImpl().SayHello(msg.toString())
                    .substring(msg.toString().lastIndexOf("#") + 1);

            ctx.writeAndFlush(response);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
