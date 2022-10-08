package Netty.rpc.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @author:wuhao
 * @description:客户端处理类
 * @date:2022/9/15
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable<String> {
    /**
     * 上下文
     */
    private ChannelHandlerContext context;

    /**
     * 返回的结果
     */
    private String result;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result = msg.toString();
        // 唤醒线程
        notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    /**
     * 客户端调用传入的参数
     */
    private String param;

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public synchronized String call() throws Exception {
        // 向服务端发送消息
        context.writeAndFlush(param);

        // 开始等待 直到服务端返回结果
        wait();

        return result;
    }


    public void setParam(String param) {
        this.param = param;
    }
}
