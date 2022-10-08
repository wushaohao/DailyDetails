package Netty.rpc.server;

/**
 * @author:wuhao
 * @description:服务端启动类
 * @date:2022/9/15
 */
public class NettyServerBootStrap {
    public static void main(String[] args) {
        NettyServer.startServer("127.0.0.1", 8080);
    }
}
