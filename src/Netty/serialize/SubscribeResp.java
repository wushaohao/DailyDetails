package Netty.serialize;

import java.io.Serializable;

/**
 * Created by wuhao on 17/6/29.
 */
public class SubscribeResp implements Serializable{
    /**
     * 默认序列号ID
     */

    private static final long SerialVersionUID=1L;

    private int subReqId;

    private int respCode;

    private String desc;

    public int getSubReqId() {
        return subReqId;
    }

    public void setSubReqId(int subReqId) {
        this.subReqId = subReqId;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString(){
        return "SubscribeResp [subReqId="+subReqId+",respCode="+respCode+",desc="+desc+"]";
    }
}
