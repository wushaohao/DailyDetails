package message;

import lombok.Data;

/**
 * @author:wuhao
 * @description:消息队列
 * @date:18/12/3
 */
@Data
public class Message {
    //消息类型
    public static final int KEY_MSG = 1;
    public static final int MOUSE_MSG = 2;
    public static final int SYS_MSG = 3;

    /**
     * 来源
     */
    private Object source;
    /**
     * 类型
     */
    private int type;
    /**
     * 信息
     */
    private String info;


    public Message(Object source, int type, String info) {
        this.source = source;
        this.type = type;
        this.info = info;
    }
}
