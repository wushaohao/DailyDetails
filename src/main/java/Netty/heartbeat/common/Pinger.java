package Netty.heartbeat.common;

import Netty.heartbeat.client.ClientIdleStateTrigger;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ScheduledFuture;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author:wuhao
 * @description:心跳发射器
 * @date:2020/3/4
 */
public class Pinger extends ChannelInboundHandlerAdapter {
  // 客户端链接到服务端后,会循环执行一个任务,随机等待几秒,然后ping一下Server端,即发送一个心跳

  private Random random = new Random();

  private int baseRandom = 8;
  private Channel channel;

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    super.channelInactive(ctx);
    this.channel = ctx.channel();
    ping(ctx.channel());
  }

  private void ping(Channel channel) {
    int second = Math.max(1, random.nextInt(baseRandom));
    // 为了避开 Connection reset by peer 异常，可以稍微修改Pinger的ping()方法，添加if (second == 5)的条件判断
      if (second == 5) {
          second = 6;
      }
    System.out.println("next heart beat will be send after " + second + "s.");
    ScheduledFuture<?> future =
        channel
            .eventLoop()
            .schedule(
                new Runnable() {
                  @Override
                  public void run() {
                    if (channel.isActive()) {
                      System.out.println("send heart beat to server");
                      channel.writeAndFlush(ClientIdleStateTrigger.HEART_BEAT);
                    } else {
                      System.out.println(
                          "This connection had broken,cancel the task that will send a heart beat");
                      channel.closeFuture();
                      throw new RuntimeException();
                    }
                  }
                },
                second,
                TimeUnit.SECONDS);

    future.addListener(
        new GenericFutureListener() {
          @Override
          public void operationComplete(Future future) throws Exception {
            if (future.isSuccess()) {
              ping(channel);
            }
          }
        });
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    // 当channel已经断开的情况下,仍然发送数据会跑出异常,该方法会被调用
    cause.printStackTrace();
    ctx.close();
  }
}
