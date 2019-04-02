package collection;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author:wuhao
 * @description:ArrayList函数测试
 * @date:2019/4/2
 */
public class ArrayListLesson1 {
    public static void main(String[] args) {
        ArrayList lists = new ArrayList();
        lists.add("add");
        lists.add("del");
        lists.add("update");
        lists.add("search");
        String[] str = new String[]{"add","del"};
        //retainAll用来判断一组集合中是否包含另一个集合中所有数据
        boolean flag = lists.retainAll(Arrays.asList(str));
        System.out.println("是否包含信息:" + flag);

    }
}
