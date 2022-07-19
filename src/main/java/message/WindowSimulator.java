package message;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author:wuhao
 * @description:窗口模拟类
 * @date:18/12/3
 */
public class WindowSimulator implements MessageProcess {

    private ArrayBlockingQueue msgQueue;

    public WindowSimulator(ArrayBlockingQueue msgQueue) {
        this.msgQueue = msgQueue;
    }


    public void GenerateMsg() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            int msgType = scanner.nextInt();
            if (msgType < 0) {
                break;
            }

            String msgInfo = scanner.next();
            Message msg = new Message(this, msgType, msgInfo);

            try {
                //新消息加入到队尾
                msgQueue.put(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 消息处理
     *
     * @param msg
     */
    @Override
    public void doMessage(Message msg) {
        switch (msg.getType()) {
            case Message.KEY_MSG:
                onKeyDown(msg);
                break;
            case Message.MOUSE_MSG:
                onMouseDown(msg);
                break;
            default:
                onSysEvent(msg);
        }
    }

    public static void onKeyDown(Message msg) {
        System.out.println("键盘事件：");
        System.out.println("type:" + msg.getType());
        System.out.println("info:" + msg.getInfo());
    }

    public static void onMouseDown(Message msg) {
        System.out.println("鼠标事件：");
        System.out.println("type:" + msg.getType());
        System.out.println("info:" + msg.getInfo());
    }

    //操作系统产生的消息
    public static void onSysEvent(Message msg) {
        System.out.println("系统事件：");
        System.out.println("type:" + msg.getType());
        System.out.println("info:" + msg.getInfo());
    }
}
