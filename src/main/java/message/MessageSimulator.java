package message;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author:wuhao
 * @description:消息模拟
 * @date:18/12/3
 */
public class MessageSimulator {
    /**
     * 消息队列
     */
    private static ArrayBlockingQueue<Message> messageQueue = new ArrayBlockingQueue<Message>(100);

    /**
     * 这里模拟用例中只有一个消息输入源，且是一种线程阻塞的，只有输入结束后才会进行消息的处理。真实的Windows操作系统中的消息机制会有多个消息输入源，
     * 且消息输入的同时也能进行消息的处理
     *
     * @param args
     */
    public static void main(String[] args) {
        WindowSimulator generator = new WindowSimulator(messageQueue);
        generator.GenerateMsg();

        Message msg = null;
        while ((msg = messageQueue.poll()) != null) {
            ((MessageProcess) msg.getSource()).doMessage(msg);
        }
    }
}
