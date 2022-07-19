package jvm.error;

/**
 * @author:wuhao
 * @description:虚拟机栈、本地方法栈:StackOverflow栈溢出(递归调用),线程请求的栈深度大于虚拟机所允许的栈深度,抛出StackOverflowError异常
 * @date:18/9/1
 */
public class StackOverflowTest {
    public static void main(String[] args) {
        method();
    }

    public static void method() {
        for (; ; ) {
            method();
        }
    }
}
