package atomic;

import java.math.BigDecimal;

/**
 * @author:wuhao
 * @description:BigDecimal基本用法
 * @date:18/10/31
 */
public class BigDecimal_1 {
    public static void main(String[] args) {
        int value =
                BigDecimal.valueOf(278)
                        .divide(BigDecimal.valueOf(100))
                        .remainder(BigDecimal.valueOf(128))
                        .divide(BigDecimal.valueOf(32))
                        .intValue();

        System.out.println("获取的结果是:" + value);

        BigDecimal b1, b2, b3;
        b1 = new BigDecimal(513.54);
        b2 = new BigDecimal(5);

        b3 = b1.remainder(b2);

        System.out.println("---------" + 513.54 % 5);

        System.out.println("b3:" + b3);
    }
}
