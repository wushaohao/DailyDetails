package basics.finalkey;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author:wuhao
 * @description:
 * @date:18/11/9
 */
public class KeyWordFinal_6 {

    public static void main(String[] args) {

        List lists = Lists.newArrayList();

        lists.add("a");
        lists.add("a");
        lists.add("a");
        lists.add("a");
        lists.add("a");

        AtomicInteger count = new AtomicInteger();
        lists.forEach(item -> {
            if ("a".equals(item)) {
                count.getAndIncrement();
            }
        });

        System.out.println(count.get());

    }
}
