package designModels.responsibilitychain.functions;

/**
 * @author:wuhao
 * @description:接口类
 * @date:2022/8/1
 */
public interface Processor {
    // 获取下一个执行的
    Processor GetNextProcessor();

    // 具体的执行
    void process(String params);
}
