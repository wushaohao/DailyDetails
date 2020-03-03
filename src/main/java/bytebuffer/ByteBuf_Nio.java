package bytebuffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author:wuhao
 * @description:ByteBuf
 * @date:2020/3/3
 */
public class ByteBuf_Nio {
  public static void main(String[] args) {
    // ByteBuf分配一个缓冲区,仅仅给一个初始值就可以默认是256。初始值不像ByteBuffer一样是最大值,ByteBuf最大值是Integer.MAX_VALUE
    ByteBuf buf = Unpooled.buffer(13);
    System.out.println(
        String.format(
            "init:readIndex=%s,writeIndex=%s,capacity=%s",
            buf.readerIndex(), buf.writerIndex(), buf.capacity()));

    /** *******写指针********* */
    String content = "ByteBuf测试";
    buf.writeBytes(content.getBytes());
    System.out.println(
        String.format(
            "write:readIndex=%s,writeIndex=%s,capacity=%s",
            buf.readerIndex(), buf.writerIndex(), buf.capacity()));

    /** ********读指针************ */
    byte[] dst = new byte[4];
    buf.readBytes(dst);
    System.out.println(new String(dst));
    System.out.println(
        String.format(
            "read(4):readIndex=%s,writeIndex=%s,capacity=%s",
            buf.readerIndex(), buf.writerIndex(), buf.capacity()));

    /** ******mark reset********* */
    // mark用来记录可能需要回滚的当前位置
    buf.markReaderIndex();
    dst = new byte[3];
    buf.readBytes(dst);
    System.out.println(
        String.format(
            "\n markRead and read(3): readIndex=%s writeIndex=%s cap=%s",
            buf.readerIndex(), buf.writerIndex(), buf.capacity()));
    // reset 是将指针回滚至 mark 记录的值
    buf.resetReaderIndex();
    System.out.println(
        String.format(
            "\n resetReaderIndex: readIndex=%s writeIndex=%s cap=%s",
            buf.readerIndex(), buf.writerIndex(), buf.capacity()));

    /** ********将读写指针重置为初始值，使用 clear() 函数********* */
    buf.clear();
    System.out.println(
        String.format(
            "\nclear: readIndex=%s writeIndex=%s cap=%s",
            buf.readerIndex(), buf.writerIndex(), buf.capacity()));

    /** ********查找字符串位置,indexOf函数,拥有三个参数,查找开始位置索引fromIndex,查询位置最大的索引toIndex,查找字节value ******** */
    int i = buf.indexOf(0, 13, Byte.parseByte("b"));
    System.out.println("[buf]索引的位置是:" + i);

    /**
     * **********copy()复制
     * 1.copy() 整个对象被复制，其所有数据都是该对象自身维护，与旧对象无任何关联关系。包括缓冲区内容，但是该方法的的容量默认为旧 buf
     * 的可读区间大小，读索引为 0，写索引为旧数据写索引的值
     * 2.copy(int index, int length) 为指定复制的起始位置及长度，其他与上面 copy() 类似
     * 3.duplicate() 这个也是复制，但是与 copy 函数不同的是，复制后生成的 ByteBuf 和旧的 ByteBuf 是共享一份缓冲区内容的。
     * 它复制的只是自己可以单独维护的一份索引。并且它复制的默认容量也是和旧的一样。
     */
    ByteBuf buf1 = buf.copy();
    System.out.println(
        String.format(
            "\ncopy: readIndex=%s writeIndex=%s cap=%s",
            buf1.readerIndex(), buf1.writerIndex(), buf1.capacity()));

    /** *********动态扩容*************** */
    System.out.println("**********ByteBuf动态扩容***************");
    ByteBuf buf2 = Unpooled.buffer(15);
    String content1 = "ByteBuf动态扩容ByteBuf动态扩容";
    buf2.writeBytes(content1.getBytes());
    System.out.println(
        String.format(
            "write扩容:writeIndex=%s,readIndex=%s,capacity=%s",
            buf2.writerIndex(), buf2.readerIndex(), buf2.capacity()));

    /**
     * ByteBuf 对象被引用后，可以调用 retain() 函数进行累计计数。每调用一次 retain() 则会 +1。
     * ByteBuf 在申请内存使用完后，需要对其进行释放，否则可能会造成资源浪费及内存泄漏的风险。这也是 ByteBuf 自己实现的一套有效回收机制。
     * 释放的函数为 release()，它的实现就是每次 -1。直到为 1 时，调用释放函数 deallocate() 进行释放.
     * 释放也支持一次释放多个引用数量,也是通过指定数量,传递给release(int decrement)进行引用数量的减少并释放对象
     */
  }
}
