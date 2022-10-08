Netty的核心组件:
组件分层:网络通信层、事件调度层、服务编排层

# 组件与组件之间的关系如下：

* 一个事件循环组(EventLoopGroup)包含多个事件循环(EventLoop)
* 一个选择器(Selector)只能注册进一个事件循环
* 一个事件循环包含一个任务队列和尾任务队列
* 一个通道(Channel)只能注册进一个选择器
* 一个通道只能绑定一个管道(Pipeline)
* 一个管道包含多个服务编排处理器(ChannelHandler)
* Netty通道和原生NIO通道一一对应并绑定
* 一个通道可以关注多个IO事件

# 网络通信层

* Bootstrap:负责客户端启动并用来链接远程Netty Server
* ServerBootstrap:负责服务端的接口监听 用来监听指定的端口
* Channel:网络通信载体

# 事件调度层

* EventLoopGroup:本质上是一个线程池 主要负责接收I/O请求
* EventLoop:相当于线程池中的单个线程

# 服务编排层

* ChannelPipeline:负责将多个ChannelHandler链接在一起
* ChannelHandler:针对I/O的数据处理器,数据接收后,通过指定的Handler进行处理
* ChannelHandlerContext:用来保存ChannelHandler的上下文信息
   
   
   