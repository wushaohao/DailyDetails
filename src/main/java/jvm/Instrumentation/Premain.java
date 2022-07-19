package jvm.Instrumentation;

import java.lang.instrument.Instrumentation;

/**
 * @author:wuhao
 * @description:
 * @date:18/7/26
 */
public class Premain {
    public static void premain(String agentArgs, Instrumentation inst) {
        /**
         * addTransformer 方法并没有指明要转换哪个类。转换发生在 premain 函数执行之后，main 函数执行之前，这时每装载一个类，
         * transform 方法就会执行一次，看看是否需要转换，
         * 所以，在 transform（Transformer 类中）方法中，程序用 className.equals("TransClass") 来判断当前的类是否需要转换
         */
        inst.addTransformer(new Transformer());
    }
}
