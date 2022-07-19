package broadcast;

import java.io.IOException;
import java.net.*;

/**
 * 广播的实现 :由客户端发出广播，服务器端接收
 *
 * @author:wuhao
 * @description:发送端程序
 * @date:18/1/18
 */
public class BroadcastTest {
    public static void main(String[] args) {
        // 广播地址
        String host = "255.255.255.255";
        // 广播目的端口
        int port = 9999;

        String message = "BroadcastTest";

        try {
            InetAddress adds = InetAddress.getByName(host);
            DatagramSocket ds = new DatagramSocket();
            DatagramPacket dp = new DatagramPacket(message.getBytes(), message.length(), adds, port);
            ds.send(dp);
            ds.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
