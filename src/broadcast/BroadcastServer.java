package broadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author:wuhao
 * @description:服务端接收程序
 * @date:18/1/18
 */
public class BroadcastServer {
    public static void main(String[] args) {
        // 开启监听端口
        int port = 9999;
        DatagramPacket dp = null;
        DatagramSocket ds = null;

        // 存储发来的消息
        byte[] buf = new byte[1024];
        StringBuffer sbuf = new StringBuffer();

        try {
            // 绑定端口
            ds = new DatagramSocket(port);
            dp = new DatagramPacket(buf, buf.length);
            System.out.println("监听端口打开....");
            ds.receive(dp);
            ds.close();
            for (int i = 0; i < 1024; i++) {
                if (buf[i] == 0) {
                    break;
                }
                sbuf.append(buf[i]);
            }
            System.out.println("收到的广播信息是:" + sbuf.toString());

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
