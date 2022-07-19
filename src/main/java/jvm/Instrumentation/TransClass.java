package jvm.Instrumentation;

/**
 * @author:wuhao
 * @description:
 * @date:18/7/26
 */
public class TransClass {
    public int getNumber() {
        return 1;
    }

    public static void main(String[] args) {
        System.out.println(new TransClass().getNumber());
    }
}
