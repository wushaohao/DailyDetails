package Netty.clientpool;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author:wuhao
 * @description:Netty客户端
 * @date:2020/3/12
 */
public class SocketClient {
  public static void main(String[] args) throws InterruptedException {
    //
    CountDownLatch countDownLatchBegin = new CountDownLatch(1);

    CountDownLatch countDownLatchEnd = new CountDownLatch(10);

    NettyChannelPool nettyChannelPool = new NettyChannelPool();

    Map<String, String> resultsMap = new HashMap<>();

    for (int i = 0; i < 10; i++) {
      new Thread(
          new Runnable() {
            @Override
            public void run() {
              try {
                countDownLatchBegin.await();
                Channel channel = nettyChannelPool.syncGetChannel();
                // 为每个线程建立一个callback,当消息返回的时候,在callback中获取结果
                CallbackService callbackService = new CallbackService();
                // 给消息分配一个唯一的消息序列号
                int seq = IntegerFactory.getInstance().incrementAndGet();
                // 利用Channel的attr方法,建立消息与callback的对应关系
                ChannelUtils.putCallback2DataMap(channel, seq, callbackService);

                synchronized (callbackService) {
                  UnpooledByteBufAllocator allocator = new UnpooledByteBufAllocator(false);
                  ByteBuf buffer = allocator.buffer(20);
                  buffer.writeInt(ChannelUtils.MESSAGE_LENGTH);

                  buffer.writeInt(seq);
                  String threadName = Thread.currentThread().getName();
                  buffer.writeBytes(threadName.getBytes());
                  buffer.writeBytes("body".getBytes());

                  // 给netty 服务端发送消息,异步的,该方法会立刻返回
                  channel.writeAndFlush(buffer);

                  // 等待返回结果
                  callbackService.wait();

                  // 解析结果,这个result在callback中已经解析到了。
                  ByteBuf result = callbackService.result;
                  int length = result.readInt();
                  int seqFromServer = result.readInt();

                  byte[] head = new byte[8];
                  result.readBytes(head);
                  String headString = new String(head);

                  byte[] body = new byte[4];
                  result.readBytes(body);
                  String bodyString = new String(body);
                  resultsMap.put(threadName, seqFromServer + headString + bodyString);
                }
              } catch (InterruptedException e) {
                e.printStackTrace();
              } finally {
                countDownLatchEnd.countDown();
              }
            }
          });
    }

      //开闸,让10个线程并发获取netty channel
      countDownLatchBegin.countDown();

      //等10个线程执行完后,打印最终结果
      countDownLatchEnd.await();
      System.out.println("resultMap="+resultsMap);
  }

    public static class CallbackService{
        public volatile ByteBuf result;
        public void receiveMessage(ByteBuf receiveBuf) throws Exception {
            synchronized (this) {
                result = receiveBuf;
                this.notify();
            }
        }
    }
}
