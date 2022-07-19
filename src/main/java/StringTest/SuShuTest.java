package StringTest;

/**
 * @author:wuhao
 * @description:1000素数
 * @date:18/9/4
 */
public class SuShuTest {
    private static final int N = 1000;

    public static boolean isShuSu(int num) {
        /**
         * 从2开始
         * Math.sqrt(i)就是调用Math函数库里的sqrt()方法，对i进行处理,具体就是对i开方,i=2时也就是根号2=1.414
         */
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int count = 0;
        for (int i = 2; i <= N; i++) {
            if (SuShuTest.isShuSu(i)) {
                System.out.println(i + "--是素数--");
                count++;
            }
        }
        System.out.println("共有素数:" + count);
    }
}
