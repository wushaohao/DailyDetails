package Netty.heartbeat.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;


/**
 * @author:wuhao
 * @description:服务器端处理器集合的初始化类
 * @date:2020/3/11
 */
public class ServerHandlerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //new IdleStateHandler(5, 0, 0)该handler代表如果在5秒内没有收到来自客户端的任何数据包（包括但不限于心跳包），将会主动断开与该客户端的连接
        ch.pipeline().addLast("idleStateHandler", new IdleStateHandler(5, 0, 0));
        ch.pipeline().addLast("idleStateTrigger", new ServerIdleStateTrigger());
        ch.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
        ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(4));
        ch.pipeline().addLast("decoder", new StringDecoder());
        ch.pipeline().addLast("encoder", new StringEncoder());
        ch.pipeline().addLast("bizHandler", new ServerBizHandler());
    }
}
