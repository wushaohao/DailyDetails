package designModels.responsibilitychain.functions;

/**
 * @author:wuhao
 * @description:执行处理类
 * @date:2022/8/1
 */
public class ProcessorImpl2 extends AbstractProcessor {
    public ProcessorImpl2(Processor processor) {
        super(processor);
    }

    @Override
    public void process(String params) {
        System.out.println("processor 2 is processing:" + params);
        if (GetNextProcessor() != null) {
            GetNextProcessor().process(params);
        }
    }
}
