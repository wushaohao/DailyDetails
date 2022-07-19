package Netty.lesson;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author:wuhao
 * @description:
 * @date:18/7/29
 */
public class MySocketChannelHandler extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast(new StringDecoder());
        channel.pipeline().addLast(new StringEncoder());
        channel.pipeline().addLast(new MyServerChannelHandler());
    }
}
