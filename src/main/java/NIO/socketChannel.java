package NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by wuhao on 17/3/20.
 * <p>
 * 2-4行表示打开一个ServerSocketChannel,设置为非阻塞模式，然后绑定一个本地端口，这样客户端便可以通过这个端口连接到服务器。
 * 6-7行表示打开一个Selector，然后将ServerSocketChannel对象注册到这个Selector实例上，注意这个ServerSocketChannel
 * 关心的事件是SelectionKey.OP_ACCEPT，表示只关心接受事件即接受客户端到服务器的连接。9-32行轮询已经就绪的事件并对具体时间进行处理，
 * 注意10行的selector.select()方法，该方法会阻塞直到selecor中注册的某个事件就绪并更新SelectionKey的状态，
 * 15行调用selector.selectedKeys()得到就绪的key集合，key中保存有就绪的事件以及对应的通道，
 * 21-23对就绪的Accept事件处理即服务器接受一个客户端连接，
 * 注意23行我们将接受的客户端连接也注册到同一个Selector对象，这样24行的Read事件可以被触发，服务器读出客户端通道的数据并写入改通道，完成echo服务。
 * 我们再梳理一下对就绪事件的处理，
 * 在最开始Selector对象中只注册了ServerSocketChannel的Accept事件，所以当第一个客户端连接到服务器时，
 * selector.select()方法不再阻塞，并执行21-23行处理客户端连接将对应通道注册到之前的Selector实例上，
 * 这样Selector实例关心的事件为：
 * (1).Accept事件（接受新客户端的连接）；
 * (2).Read事件（读取老客户端发送给服务器的数据）。
 * 因此，当有新客户端连接时执行21-23行，当有新客户端发来数据时执行25-29行。
 */
public class socketChannel {

    public static void startServer(int port) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();//打开一个ServerSocketChannel
        serverChannel.configureBlocking(false);// 设置ServerSocketChannel为非阻塞模式
        serverChannel.bind(new InetSocketAddress(port));// 绑定到设置的端口

        Selector selector = Selector.open();    // 打开selector
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int readyChannels = selector.select();  // 阻塞直到有IO事件就绪
            if (readyChannels <= 0) {
                continue;
            }

            Set<SelectionKey> SelectorKeySet = selector.selectedKeys(); // 得到就绪的选择键
            Iterator<SelectionKey> iterator = SelectorKeySet.iterator();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (iterator.hasNext()) {    // 遍历选择键
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {   // 处理accept事件
                    SocketChannel socketChannel = serverChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);// 将接受的客户端连接也注册到同一个Selector对象
                } else if (key.isReadable()) {  // 处理read事件
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    buffer.clear();
                    socketChannel.read(buffer);//服务器读出客户端通道的数据
                    buffer.flip();
                    socketChannel.write(buffer);//服务器写入该通道
                }
                iterator.remove();
            }
        }
    }
}
