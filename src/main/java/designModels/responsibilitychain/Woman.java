package designModels.responsibilitychain;

/**
 * @author:wuhao
 * @description:妇女类
 * @date:18/7/25
 */
public class Woman implements IWoman {
    private int type = 0;
    private String request = "";

    /**
     * 获得当前状态
     *
     * @return
     */
    @Override
    public int getType() {
        return this.type;
    }

    /**
     * 获得个人请示  你要干什么
     *
     * @return
     */
    @Override
    public String getResponse() {
        return this.request;
    }

    public Woman(int type, String request) {
        this.type = type;
        switch (this.type) {
            case 1:
                this.request = "女儿的请求是:" + request;
                break;
            case 2:
                this.request = "妻子的请求是:" + request;
                break;
            case 3:
                this.request = "母亲的请求是:" + request;
                break;

        }
    }
}
