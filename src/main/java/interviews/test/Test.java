package interviews.test;

import java.util.Scanner;

/**
 * @author:wuhao
 * @description:
 * @date:2020/4/10
 */
public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String arthLine = scanner.nextLine();
            if (arthLine.trim().length() != 0) {
                System.out.println(isTrue(arthLine));
            }
        }
    }

    public static boolean isTrue(String str) {
        String[] arrays = str.split("\\s+");
        if (arrays[0].length() == 2 && arrays[arrays.length - 1].length() == 2) {
            for (int i = 1; i < arrays.length - 1; i++) {
                if (arrays[i].length() == 1) {
                    return true;
                }
            }
        }

        for (int i = 0; i < arrays.length; i++) {
            System.out.println(arrays[i]);
            if (i != arrays.length - 1) {
                if (arrays[i].length() == 1 && arrays[i + 1].length() == 2 || arrays[i].length() == 2 && arrays[i + 1].length() == 1) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
