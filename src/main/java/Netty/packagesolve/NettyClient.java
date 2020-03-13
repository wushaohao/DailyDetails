package Netty.packagesolve;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.Date;

/**
 * @author:wuhao
 * @description:Netty客户端
 * @date:2020/3/13
 */
public class NettyClient {
  public void send() {
    Bootstrap bootstrap = new Bootstrap();

    NioEventLoopGroup workGroup = new NioEventLoopGroup();

    bootstrap
        .group(workGroup)
        .channel(NioSocketChannel.class)
        .option(ChannelOption.TCP_NODELAY, true)
        .handler(
            new ChannelInitializer<NioSocketChannel>() {
              @Override
              protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                ChannelPipeline pipeline = nioSocketChannel.pipeline();
                pipeline
                    .addLast(new LineBasedFrameDecoder(1024))
                    // StringDecoder():将对象转换成字符串
                    .addLast(new StringDecoder())
                    .addLast("logger", new LoggingHandler(LogLevel.INFO))
                    .addLast(new ClientHandler());
              }
            });

    Channel channel = bootstrap.connect("localhost", 8090).channel();

    int i = 1;
    while (i < 300) {
      String str =
          String.format("【时间 %s:\t%s", new Date(), i) + System.getProperty("line.separator");
      byte[] bytes = str.getBytes();

      ByteBuf buf = Unpooled.buffer(bytes.length);
      buf.writeBytes(bytes);

      channel.writeAndFlush(buf);
      // 打印发送的请求次数
      System.out.println(i);
      i++;
    }

    if (workGroup != null) {
      workGroup.shutdownGracefully();
    }
  }
}
