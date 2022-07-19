package google.guava.collections;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.*;

import java.util.*;

/**
 * @author:wuhao
 * @description:集合创建
 * @date:18/11/1
 */
public class CollectionsDemo {
    public static void main(String[] args) {
        //普通Collection的创建
        List<String> list = Lists.newArrayList();
        Set<String> set = Sets.newHashSet();
        Map<String, String> map = Maps.newHashMap();

        // 不变Collection的创建
        ImmutableList<String> iList = ImmutableList.of("a", "b", "c");
        ImmutableSet<String> iSet = ImmutableSet.of("e1", "e2");
        ImmutableMap<String, String> iMap = ImmutableMap.of("k1", "v1", "k2", "v2");

        /**
         * 创建不可变集合
         * 先理解什么是immutable(不可变)对象
         * 1.在多线程操作下，是线程安全的。
         * 2.所有不可变集合会比可变集合更有效的利用资源。
         * 3.中途不可改变
         */


        //当我们需要一个map中包含key为String value为List类型的时候 以前我们是这样写的
        Map<String, List<Integer>> mapss = new HashMap<String, List<Integer>>();
        List<Integer> listss = new ArrayList<Integer>();
        listss.add(1);
        listss.add(2);
        mapss.put("aa", listss);
        System.out.println(mapss.get("aa"));

        //guava
        Multimap<String, Integer> multiMap = ArrayListMultimap.create();
        multiMap.put("aa", 1);
        multiMap.put("aa", 2);
        System.out.println(multiMap.get("aa"));


        //以前我们将list转换为特定规则的字符串是这样写的:
        List<String> orgList = new ArrayList<String>();
        orgList.add("aa");
        orgList.add("bb");
        orgList.add("cc");
        String str = "";
        for (int i = 0; i < orgList.size(); i++) {
            str = str + "-" + orgList.get(i);
        }
        //Original:-aa-bb-cc
        System.out.println("Original:" + str);

        //guava
        List<String> gList = Lists.newArrayList();
        gList.add("aa");
        gList.add("bb");
        gList.add("cc");
        String results = Joiner.on("-").join(gList);
        //Guava:aa-bb-cc
        System.out.println("Guava:" + results);


        //把map集合转换为特定规则的字符串
        Map<String, Integer> gMap = Maps.newHashMap();
        gMap.put("xiaoming", 12);
        gMap.put("xiaohong", 13);
        String gResult = Joiner.on(",").withKeyValueSeparator("=").join(gMap);
        System.out.println("gResult:" + gResult);


        //将String转换为特定的集合
        //use java
        List<String> orgList1 = new ArrayList<String>();
        String a = "1-2-3-4-5-6";
        String[] strs = a.split("-");
        for (int i = 0; i < strs.length; i++) {
            orgList1.add(strs[i]);
        }
        //use guava
        String guavaStr = "1-2-3-4-5-6";
        String guavaStr2 = "1-2-3-4- 5- 6 ";
        List<String> gReList = Splitter.on("-").splitToList(guavaStr);
        //使用 “-“ 切分字符串并去除空串与空格== omitEmptyStrings().trimResults() 去除空串与空格
        List<String> gReList2 = Splitter.on("-").omitEmptyStrings().trimResults().splitToList(guavaStr2);
        //guavaList:[1, 2, 3, 4, 5, 6]
        System.out.println("guavaList:" + gReList);
        //guavaList2:[1, 2, 3, 4, 5, 6]
        System.out.println("guavaList2:" + gReList2);

        //将String转换为map
        String strX = "xiaoming=11,xiaohong=23";
        Map<String, String> mapX = Splitter.on(",").withKeyValueSeparator("=").split(strX);
        //mapX:{xiaoming=11, xiaohong=23}
        System.out.println("mapX:" + mapX);

        //guava还支持多个字符切割，或者特定的正则分隔
        String input = "aa.dd,,ff,,.";
        List<String> result = Splitter.onPattern("[.|,]").omitEmptyStrings().trimResults().splitToList(input);
        //result:[aa, dd, ff]
        System.out.println("result:" + result);

        // 判断匹配结果
        boolean bol = CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z')).matches('K');
        // 保留数字文本
        String s1 = CharMatcher.digit().retainFrom("abc 123 cde");
        // 删除数字文本
        String s2 = CharMatcher.digit().removeFrom("abc 123 cde");
        System.out.println(bol + "\t" + s1 + "\t" + s2);
    }
}
