package StringTest;

/**
 * @author:wuhao
 * @description:
 * @date:18/9/27
 */
public class tests {
    public void test(int i) {
        if (i <= 1 || i >= 10) {
            return;
        } else if (i % 2 == 0) {
            System.out.println("try:" + i);
            test(i - 2);
        } else {
            test(i + 1);
            System.out.println("try:" + i);
        }
    }

    public static void main(String[] args) {
        new tests().test(5);

    }
}
