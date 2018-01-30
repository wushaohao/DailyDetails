package broadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @author:wuhao
 * @description:组播服务接收端
 * @date:18/1/18
 */
public class MultiBroadcastServer {
    private static MulticastSocket multicastSocket;
    private static String host = "239.0.0.255";
    private static InetAddress receiveAddress;

    private static int port = 8099;
    public static void main(String[] args) throws IOException {
        multicastSocket = new MulticastSocket(port);
        receiveAddress = InetAddress.getByName(host);
        // 广播到具体的组
        multicastSocket.joinGroup(receiveAddress);
        new Thread(new udpRunnable(multicastSocket)).start();

    }
}

class  udpRunnable implements Runnable{

    MulticastSocket ms;

    public udpRunnable(MulticastSocket ms) {
        this.ms = ms;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, buf.length);
        while (true) {
            try {
                ms.receive(dp);
                System.out.println("receive client message:"+new String(buf,0,dp.getLength()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
