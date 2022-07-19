package designModels.responsibilitychain;

/**
 * @author:wuhao
 * @description:女性接口
 * @date:18/7/25
 */
public interface IWoman {
    /**
     * 获得当前状态
     *
     * @return
     */
    int getType();

    /**
     * 获得个人请示  你要干什么
     *
     * @return
     */
    String getResponse();
}
