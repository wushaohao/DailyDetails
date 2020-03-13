1.LineBasedFrameDecoder:换行符处理
  LineBasedFrameDecoder 时，换行分隔符必须加，否则接收消息端收不到消息，如果手写换行分割，要记得区分不同系统的适配


2.DelimiterBasedFrameDecoder:自定义分隔符
  // 指定的分隔符
  public static final String DELIMITER ="$@$";

  // 如果当前数据2048个字节中没有分隔符，就会抛出异常，避免内存溢出。也可以自定义预检查当前读取的数据，自定义这里超过的规则
  pipeline.addLast(new DelimiterBasedFrameDecoder(2048,Unpooled.wrappedBuffer(DELIMITER.getBytes()))// 分割符缓冲对象);

3.FixedLengthFrameDecoder:根据固定长度
  设定固定长度，进行数据传输，如果不达固定长度，使用空格补全。服务端和客户端均在 pipeline 添加 FixedLengthFrameDecoder:
  // 100为指定的固定长度
  ch.pipeline().addLast(new FixedLengthFrameDecoder(100));
  每次读取数据时都会按照 FixedLengthFrameDecoder 中设置的固定长度进行解码，如果出现粘包，那么会进行多次解码，如果出现拆包的情况，那么 FixedLengthFrameDecoder 会先缓存当前部分包的信息，当接收下一个包时，会与缓存的部分包进行拼接，知道满足规定的长度.

4.动态指定长度:LengthFieldBasedFrameDecoder
动态指定长度就是说，每条消息的长度都是随着消息头进行指定，这里使用的编码器为 LengthFieldBasedFrameDecoder
pipeline().addLast(new LengthFieldBasedFrameDecoder(2048,// 帧的最大长度，即每个数据包最大限度
                                     0,// 长度字段偏移量
                                     4,// 长度字段所占的字节数
                                     0,// 消息头的长度，可以为负数
                                     4)// 需要忽略的字节数，从消息头开始，这里是指整个包);
