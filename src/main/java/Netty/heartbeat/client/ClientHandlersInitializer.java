package Netty.heartbeat.client;

import Netty.heartbeat.common.Pinger;
import Netty.heartbeat.common.ReconnectHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.junit.Assert;

/**
 * @author:wuhao
 * @description:客户端处理器集合的初始化类
 * @date:2020/3/11
 */
public class ClientHandlersInitializer extends ChannelInitializer<SocketChannel> {

  private ReconnectHandler reconnectHandler;

//  private EchoHandler echoHandler;

  public ClientHandlersInitializer(TcpClient tcpClient) {
    Assert.assertNotNull(tcpClient);
    this.reconnectHandler = new ReconnectHandler(tcpClient);
//    this.echoHandler = new EchoHandler();
  }

  @Override
  protected void initChannel(SocketChannel socketChannel) throws Exception {
    ChannelPipeline pipeline = socketChannel.pipeline();
    pipeline.addLast(this.reconnectHandler);
    pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
    pipeline.addLast(new LengthFieldPrepender(4));
    pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
    pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
    pipeline.addLast(new Pinger());
  }
}
