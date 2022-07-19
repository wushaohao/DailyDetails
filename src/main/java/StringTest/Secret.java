package StringTest;

import java.util.*;

public class Secret {
    private int number = new Random().nextInt(10);

    public boolean guess(int candidate) {
        return number == candidate;
    }

    public static void main(String[] args) {
        new Secret().guess(9);
        int[] x = new int[25];
        System.out.println(x[24]);
    }
}