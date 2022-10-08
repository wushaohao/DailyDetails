package designModels.responsibilitychain.functions;

import java.util.function.Consumer;

/**
 * @author:wuhao
 * @description:
 * @date:2022/8/1
 */
public class Client {
    public static void main(String[] args) {
        Processor processor = new ProcesserImpl1(null);
        Processor processor2 = new ProcessorImpl2(processor);
        processor2.process("Chain model");


        Consumer<String> p1 = param -> System.out.println("processor 1 is processing:" + param);
        Consumer<String> p2 = param -> System.out.println("processor 2 is processing:" + param);
        p2.andThen(p1).accept("Chain model function");
    }
}
