package collection;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 *
 * @author wuhao
 * @date 17/3/12
 */
public class demo1 {
    public static void main(String[] args) {
        String[] str={"curry","curry1","curry1","curry2","curry2"};
        List<String> lists=new ArrayList<>();

        for (int i = 0; i < str.length; i++) {
            lists.add(str[i]);
        }

        System.out.println("list集合的大小是:"+lists.size());

        /**
         * list 去从
         */
        Set<String> sets=new HashSet<>(lists);
        lists.clear();
        lists.addAll(sets);
        System.out.println("set集合的大小是:"+sets.size()+"\t lists的的大小是:"+lists.size());


        /**
         * 拷贝list
         *
         * 需要注意的是，使用该方法的话目标list至少跟源list长度一样长。否则会报IndexOutOfBoundsException异常。
         *另外有两点需要注意
         * 1.两种方法都是浅拷贝
         * 2.Collections.copy()方法的两个参数必须都是list，而ArrayList方法参数只要是collection即可，因此ArrayList方法更通用。
         */
        List<String> destList2=new ArrayList<>(lists);
        System.out.println("destList2集合的长度是:"+destList2.size());
        for (String ss:destList2) {
            System.out.println("遍历的结果是:"+ss);
        }

        /**
         * destList.size()才知道des1的长度为0；3表示的是这个List的容纳能力为3，并不是说des1中就有了3个元素。查看api才知道，
         * 它的capacity（容纳能力大小）可以指定（最好指定）。而初始化时size的大小永远默认为0，只有在进行add和remove等相关操作时，
         * size的大小才变化。然而进行copy()时候，首先做的是将desc1的size和src1的size大小进行比较，
         * 只有当desc1的size 大于或者等于src1的size时才进行拷贝，否则抛出IndexOutOfBoundsException异常。
         * 所以可以通过下面的方法指定目标desc的大小
         * List des1=new ArrayList(Arrays.asList(new object[src1.size]));//注意：new ArrayList(Collection col)参数必须要实现Collection 接口。
         * Collections.copy(des1,src1);
         */
        List<String> destList=new ArrayList<>(Arrays.asList(new String[lists.size()]));
        System.out.println("destList的集合长度是:"+destList.size());
        Collections.copy(destList,lists);
        System.out.println("copy的集合长度是:"+destList.size());


        /**
         * 使用apache的CollectionUtils.addAll()
         */
        List<String> destList3 = new ArrayList();
        CollectionUtils.addAll(destList3, new Object[lists.size()]);
        Collections.copy(destList3, lists);
        System.out.println("destList3的大小是:"+destList3.size());

    }
}
