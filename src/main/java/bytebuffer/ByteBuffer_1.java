package bytebuffer;

import java.nio.ByteBuffer;

/**
 * @author:wuhao
 * @description:ByteBuffer测试
 * @date:2020/3/3
 */
public class ByteBuffer_1 {
  public static void main(String[] args) {
    // 分配一个缓冲区
    ByteBuffer buffer = ByteBuffer.allocate(100);
    // 设置当前缓冲区大小限制
    buffer.limit(15);

    //position:读写指针的位置 limit:当前缓存区大小限制 capacity:缓冲区大小
    System.out.println(
        String.format(
            "allocate:pos=%s,limit=%s,cap=%s",
            buffer.position(), buffer.limit(), buffer.capacity()));
    String content = "测试buffer";
    // 向缓冲区写入数据
    buffer.put(content.getBytes());

    // 写入内容后读写指针的位置
    System.out.println(
        String.format("put:pos=%s,limit=%s,cap=%s", buffer.position(),
        buffer.limit(),
        buffer.capacity()));

    /** ***********读取内容***************** */
    System.out.println("******************************");
      /**
       * 在写入数据后再读取内容必须主动调用ByteBuffer.flip()或者ByteBuffer.clear()
       * flip它会将写入数据后的指针位置值作为当前缓冲区大小,再将指针位置归零。会使写入数据的缓冲区改为待取数据的缓冲区,
       * 也就是说,读取数据会从刚写入的数据第一个索引作为读取数据的起始索引
        */
    buffer.flip();
    System.out.println(
        String.format(
            "flip:position=%s,limit=%s,cap=%s",
            buffer.position(), buffer.limit(), buffer.capacity()));
    // 读取内容需要创建byte数组来接收,并制定接收的数据大小
    byte[] readBytes = new byte[4];
    buffer.get(readBytes);

    System.out.println(
        String.format(
            "get(4):position=%s,limit=%s,capacity=%s",
            buffer.position(), buffer.limit(), buffer.capacity()));
    String readContent = new String(readBytes);
    System.out.println("readContent=" + readContent);

    /** ******clear来读区********* */
    System.out.println("**************clear**************");
      /**
       * clear会重置limit为默认值,与capacity大小相同,第二次读取的时候可以使用remaining来获取大于或等于剩下的内容的字节大小
       * 该函数实现为limit-position,所以当前缓冲区一定在这个值区域内。
       */
    buffer.clear();
    byte[] readClear = new byte[buffer.remaining()];
    buffer.get(readClear);
    System.out.println(
        String.format(
            "get(remaining):pos=%s,limit=%s,capacity=%s",
            buffer.position(), buffer.limit(), buffer.capacity()));
    String clearContent = new String(readClear);
    System.out.println("clear="+clearContent);

    /** *******ByteBuf动态扩容*************** */
    System.out.println("********ByteBuffer动态扩容测试***********");
    ByteBuffer buffer1 = ByteBuffer.allocate(100);
    buffer1.limit(15);
    // 出现异常:java.nio.BufferOverflowException,内容字节大小超过了limit的值时,所以我们每次写入数据前,得检查缓存区大小是否有足够空间大小,这样对编码来说体验不是太好呀
    String content1 = "ByteBuffer动态扩容ByteBuffer动态扩容";
    buffer1.put(content1.getBytes());
      System.out.println(
              String.format(
                      "allocate:pos=%s,limit=%s,cap=%s",
                      buffer.position(), buffer.limit(), buffer.capacity()));
  }
}
