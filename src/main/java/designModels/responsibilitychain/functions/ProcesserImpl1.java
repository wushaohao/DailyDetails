package designModels.responsibilitychain.functions;

/**
 * @author:wuhao
 * @description:具体的执行处理类
 * @date:2022/8/1
 */
public class ProcesserImpl1 extends AbstractProcessor {
    public ProcesserImpl1(Processor processor) {
        super(processor);
    }

    @Override
    public void process(String params) {
        System.out.println("processor 1 is processing:" + params);
        if (GetNextProcessor() != null) {
            GetNextProcessor().process(params);
        }
    }
}
