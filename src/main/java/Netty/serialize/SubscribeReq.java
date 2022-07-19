package Netty.serialize;

import java.io.Serializable;

/**
 * Created by wuhao on 17/6/29.
 */
public class SubscribeReq implements Serializable {

    /**
     * 默认序列号ID
     */
    private static final long SerialVersionUID = 1L;

    private int subReqId;

    private String userName;

    private String productName;

    private String phoneNum;

    private String address;

    public int getSubReqId() {
        return subReqId;
    }

    public void setSubReqId(int subReqId) {
        this.subReqId = subReqId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return "SubscribeReq [sunReqId=" + subReqId + ",userName" + userName + ",productName" + productName + ",address" + address + "]";
    }
}
