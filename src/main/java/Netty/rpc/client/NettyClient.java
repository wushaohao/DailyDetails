package Netty.rpc.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;
import java.util.concurrent.*;

/**
 * @author:wuhao
 * @description:Netty客户端
 * @date:2022/9/15
 */
public class NettyClient {

    private static ThreadPoolExecutor poolExecutor;
    /**
     * 长度固定为4的线程队列
     */
    private static ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(4);

    private static NettyClientHandler clientHandler;

    private static final String HOST_IP = "127.0.0.1";

    private static final int PORT = 8080;


    /**
     * 客户端初始化
     */
    private static void init() {
        poolExecutor = new ThreadPoolExecutor(2, 6, 1, TimeUnit.SECONDS, queue);
        clientHandler = new NettyClientHandler();

        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        try {
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(clientHandler);

                        }
                    }).connect(HOST_IP, PORT).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public Object getBean(final Class<?> serviceClass, final String providerName) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class<?>[]{serviceClass}, ((proxy, method, args) -> {
                    if (clientHandler == null) {
                        init();
                    }
                    // 设置放松给服务端的信息
                    clientHandler.setParam(providerName + args[0]);
                    return poolExecutor.submit(clientHandler).get();
                }));
    }


}
