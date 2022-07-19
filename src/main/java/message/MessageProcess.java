package message;

/**
 * @author:wuhao
 * @description:消息处理接口
 * @date:18/12/3
 */
public interface MessageProcess {
    /**
     * 消息处理
     *
     * @param msg
     */
    void doMessage(Message msg);
}
