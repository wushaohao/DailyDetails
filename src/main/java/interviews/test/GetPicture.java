package interviews.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:wuhao
 * @description:获取最合适的图片
 * @date:2022/8/5
 */
public class GetPicture {

    public static void main(String[] args) {
        System.out.println("Match the Picture is:" + computer(1400, 2000));
    }

    public static String computer(int width, int height) {
        float A = 1280 / 2560;
        float B = 1600 / 2560;
        float C = 1800 / 2060;

        float toCheck = width / height;

        float A1 = Math.abs(toCheck - A);
        float B1 = Math.abs(toCheck - B);
        float C1 = Math.abs(toCheck - C);
        Map<Float, String> maps = new HashMap<>();
        maps.put(A1, "A");
        maps.put(B1, "B");
        maps.put(C1, "C");

        float temp = 0;
        if (A1 > B1) {
            temp = A1;
            A1 = B1;
            B1 = temp;

        }
        if (A1 > C1) {
            temp = A1;
            A1 = C1;
            C1 = temp;

        }
        if (B1 > C1) {
            temp = B1;
            B1 = C1;
            C1 = temp;
        }

        return maps.get(toCheck);
    }
}
