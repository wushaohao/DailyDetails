package StringTest;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Created by wuhao on 17/5/12.
 */
public class test_01 {

    private static final String IMPL_CLASS_STRING_TEMPLATE = "%sImpl";

    private static String regionId;

    public static String getRegionId() {
        return regionId;
    }

    public static void setRegionId(String regionId) {
        test_01.regionId = regionId;
    }

    public static void main(String[] args) {

        System.out.println(Integer.parseInt("128") == Integer.valueOf("128"));
        System.out.println(Integer.valueOf("128") == Integer.valueOf("128"));
        System.out.println(Integer.valueOf("128") == Integer.valueOf("128"));

        setRegionId("A");
        System.out.println(getRegionId());
        setRegionId("H");
        System.out.println(getRegionId());

        // 获取类文件的完整路径
        String classPath = Car.class.getName();
        System.out.println("类文件路径是:" + classPath);
        String str = String.format(IMPL_CLASS_STRING_TEMPLATE, classPath);
        System.out.println("结果是:" + str);

        // 接口类路径
        String interPath = "com.ai.aif.csf.ord.interfaces.ICsfOrderTestCSV";
        int index = interPath.lastIndexOf(".");
        String interfaceName = interPath.substring(index + 1, interPath.length());
        String path = interPath.substring(0, index);
        System.out.println("path:" + path);
        int index2 = path.lastIndexOf(".");
        String implPath = path.substring(0, index2 + 1);
        //实现类路径
        String implName = implPath + "impl." + interfaceName.substring(1, interfaceName.length()) + "Impl";
        System.out.println("类路径是:" + implName);

        // format
        String results = String.format("/AICACHE_LOAD/%s/%s", "CSF", "com.ai.aif.csf.db.cache.impl.CsfSrvServiceInfoCacheImpl");
        System.out.println("format结果是:" + results);

        // 1100
        /**
         * 用来将一个数的各二进制位全部右移若干位.例如:a   =   a>>2,使a的各二进制位右移两位,移到右端的低位被舍弃,最高位则移入原来高位的值.
         如:a   =   00110111,则a>>2=00001101,b=11010011,则b>>2   =   11110100
         右移一位相当于除2   取商,而且用右移实现除法比除法运算速度要快
         */
        int a = 12;
        System.out.println(Integer.toBinaryString(a >> 1));
        System.out.println(Integer.toBinaryString(a << 1));
        /**
         * >>>
         无符号右移，忽略符号位，空位都以0补齐
         value >>> num     --   num 指定要移位值value 移动的位数。
         无符号右移的规则只记住一点：忽略了符号位扩展，0补最高位  无符号右移运算符>>> 只是对32位和64位的值有意义
         */
        System.out.println(Integer.toBinaryString(a >>> 1));


        //StringUtils.substringAfterLast()
        List<String> list = new ArrayList<>();
        list.add("NBOSS^891^000000000737757");
        list.add("NBOSS^891^000000000737691");
        list.add("NBOSS^891^000000000737698");
        int size = list.size();
        String workflowId = "NBOSS^891^000000000737757";
        String temp = StringUtils.substringAfterLast(workflowId, "^");
        System.out.println("temp:" + temp);
        long unWrapId = Long.parseLong(temp);
        System.out.println("unWrapId:" + unWrapId);
        int tagIndex = (int) (unWrapId % (size + 1));
        System.out.println("tagIndex:" + tagIndex);
        if (tagIndex == size) {
            System.out.println("没有值...");
        }
        System.out.println("获取的值是:" + list.get(tagIndex));

        String repName = StringUtils.replace("prefetch.driverClassName", "prefetch.", "").trim();
        System.out.println("repName:" + repName);

        get("input/fee");

        String id = "NBOSS^891^000000000737698";
        String before = StringUtils.substringBefore(id, "^");
        String between = StringUtils.substringBetween(id, "^", "^");
        System.out.println("before:" + before + "\n" + "between:" + between);

        int requestLen = Integer.parseInt(System.getProperty("csf.protocol.frameMaxLength", "16777216"));
        System.out.println("请求长度:" + requestLen);
    }

    public static void get(String path) {
        path = StringUtils.trim(path);
        if (StringUtils.isEmpty(path)) {
            return;
        }


        String[] paths = StringUtils.split(path, "/");

        for (String one : paths) {
            if (StringUtils.isEmpty(one)) {
                continue;
            }

            String onePath = one;
            String functions = StringUtils.EMPTY;
            System.out.println("one:" + one);
            //支持表达式
            Matcher matcher = Patterns.TreeNodePatterns.FUNCTION_PATTERN.matcher(one);
            if (matcher.matches()) {
                //第一个分组是路径
                onePath = matcher.group(1);
                //第二个分组是配置的表达式
                functions = matcher.group(2);
                System.out.println(onePath + ">>>>>>>>>>>" + functions);
            }

            //针对根路径的特殊情况需要考虑/{upperFirst(*);lowerFirst(*);convert(key1=key2,key3=key4)}
            //只有根路径上配置表达式的时候才会走到该分支
            if (StringUtils.isEmpty(onePath)) {
                System.out.println("onePath" + onePath);
                break;
            }

            //节点上配置的表达式,这个表达式不能配置到叶子节点上，叶子节点上没有key值，只有value值； 我们是通过map的key值来定义路径的
            String function = StringUtils.trim(functions);
            System.out.println("function:" + function);
        }
    }


}
