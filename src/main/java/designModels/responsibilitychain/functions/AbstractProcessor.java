package designModels.responsibilitychain.functions;


/**
 * @author:wuhao
 * @description:抽象类
 * @date:2022/8/1
 */
public abstract class AbstractProcessor implements Processor {
    private Processor next;


    public AbstractProcessor(Processor processor) {
        this.next = processor;
    }


    @Override
    public Processor GetNextProcessor() {
        return next;
    }

    @Override
    public abstract void process(String params);
}
