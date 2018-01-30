package Netty.timeout;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author wuhao
 * @date 17/5/18
 */
public class HeartbeatHandlerInitializer extends ChannelInitializer<Channel>{
    /**
     * 读超时
     */
    private static  final int READ_IDEL_TIME_OUT=4;
    /**
     * 写超时
     */
    private static  final int WRITE_IDEL_TIME_OUT=4;
    /**
     * 读写超时
     */
    private static  final int ALL_IDEL_TIME_OUT=4;


    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline=channel.pipeline();
        // 使用了 IdleStateHandler ，分别设置了读、写超时的时间
        pipeline.addLast(new IdleStateHandler(READ_IDEL_TIME_OUT,WRITE_IDEL_TIME_OUT,ALL_IDEL_TIME_OUT, TimeUnit.SECONDS));
        // 定义了一个 HeartbeatServerHandler 处理器，用来处理超时时，发送心跳
        pipeline.addLast(new HeartbeatServerHandler());

    }
}
