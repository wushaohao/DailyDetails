package exception;

import java.io.IOException;

/**
 * @author:wuhao
 * @Description:
 * @Date:17/11/13
 */
public class Demo1 {
    private void fun1() throws IOException {
        throw new IOException("level 1 exception");
    }
    private void fun2() {
        try {
            fun1();
        } catch (IOException e) {
            throw new RuntimeException("level 2 exception", e);
        }
    }
    public static void main(String[] args) {
        try {
            new Demo1().fun2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
