package Netty.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * EventLoopGroup: 不论是服务器端还是客户端, 都必须指定 EventLoopGroup. 在这个例子中, 指定了 NioEventLoopGroup, 表示一个 NIO 的EventLoopGroup.
 * ChannelType: 指定 Channel 的类型. 因为是客户端, 因此使用了 NioSocketChannel.
 * Handler: 设置数据的处理器.
 * @author wuhao
 */
public class NettyClient {

	/**
     * 服务器端口号
	 */
	private int port;

	/**
	 * 服务器IP
	 */
	private String host;

	public NettyClient(int port, String host)
			throws InterruptedException {
		this.port = port;
		this.host = host;
		start();
	}

	private void start() throws InterruptedException {
		
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		
		try {
			
			Bootstrap bootstrap = new Bootstrap();

			bootstrap.group(eventLoopGroup).
					channel(NioSocketChannel.class).
					option(ChannelOption.SO_KEEPALIVE, true).
					remoteAddress(host, port).
					handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel socketChannel)
						throws Exception {					
					socketChannel.pipeline().addLast(new NettyClientHandler());
				}
			});
			ChannelFuture future = bootstrap.connect(host, port).sync();
			if (future.isSuccess()) {
				SocketChannel socketChannel = (SocketChannel) future.channel();
				System.out.println("----------------connect server success----------------");
			}
			future.channel().closeFuture().sync();
		} finally {
			eventLoopGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		
		NettyClient client = new NettyClient(9999, "localhost");

	}
}