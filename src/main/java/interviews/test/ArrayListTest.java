package interviews.test;

import java.util.ArrayList;

/**
 * @author:wuhao
 * @description:ArrayList泛型擦除
 * @date:2020/5/17
 */
public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<String> strList = new ArrayList<>();
        strList.add("abc");

        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(123);

        System.out.println(strList.getClass());
        System.out.println(strList.getClass() == intList.getClass());

    }
}
