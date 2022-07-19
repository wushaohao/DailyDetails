package StringTest;

import org.apache.commons.lang.StringUtils;

/**
 * @author:wuhao
 * @description:StringBuilder清空操作
 * @date:18/12/7
 */
public class StringBulider_Demo {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("I Love You");
        sb.append("Hulk");
        System.out.println("StringBuilder的长度:" + sb.length());

//        sb.delete(0, sb.length());
        sb.setLength(0);

        boolean isEmpty = StringUtils.isNotBlank(sb.toString());
        System.out.println("StringBuilder是否为空:" + isEmpty + "\t" + "内容是:" + sb.toString());
        System.out.println("StringBuilder删除后的长度:" + sb.length());
    }
}
