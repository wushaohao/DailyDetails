package Netty.rpc.client;

import Netty.rpc.service.HelloService;

/**
 * @author:wuhao
 * @description:客户端启动类
 * @date:2022/9/15
 */
public class ClientBootStrap {

    private static String DEFAULT = "HelloService#hello#";

    public static void main(String[] args) {
        //创建消费者
        NettyClient client = new NettyClient();


        //创建代理对象
        HelloService helloService = (HelloService) client.getBean(HelloService.class, DEFAULT);

        String res = helloService.SayHello("hello netty rpc");

        System.out.println("Call Rpc result is:" + res);
    }
}
