package broadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @author:wuhao
 * @description:组播发送类
 * @date:18/1/18
 */
public class MultiBroadcastSendup {
    public static void main(String[] args) {
        MulticastSocket ms = null;
        DatagramPacket dp = null;
        int port = 8099;

        try {
            ms = new MulticastSocket();
            ms.setTimeToLive(32);
            ////将本机的IP（这里可以写动态获取的IP）地址放到数据包里，其实server端接收到数据包后也能获取到发包方的IP的
            byte[] data = "组播测试".getBytes();
            InetAddress address = InetAddress.getByName("239.0.0.255");
            dp = new DatagramPacket(data, data.length, address, port);
            ms.send(dp);
            ms.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
