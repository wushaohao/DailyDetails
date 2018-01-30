package Netty.stickpackage.normal;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 *
 * @author wuhao
 * @date 17/6/19
 */
public class TimeServer {

    public void bind(int port) throws Exception{
        // 配置服务端线程NIO线程组
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workGroup=new NioEventLoopGroup();

        try{
            ServerBootstrap b=new ServerBootstrap();
            b.group(bossGroup,workGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024).childHandler(new ChildChannelHandler());
            //绑定端口
            ChannelFuture channelFuture=b.bind(port).sync();
            //等待服务端坚挺端口关闭
            channelFuture.channel().closeFuture().sync();
        }finally {
            // 优雅退出，释放资源
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new TimerServerHandle());
        }
    }

    public static void main(String[] args) {
        int port=8099;
        if (args!=null && args.length>0){
            port=Integer.valueOf(args[0]);
        }
        try {
            new TimeServer().bind(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
