package StringTest;

import org.apache.commons.lang.StringUtils;

import java.util.UUID;

/**
 * Created by wuhao on 16/11/15.
 */
public class StringTestDemo1 {

    public static void main(String[] args) {
        String requestUrl = "TstServer/helloworld/business/getmethod";

        String methodName = requestUrl.substring(requestUrl.lastIndexOf("/") + 1);

        System.out.println("获取到的方法名称是:" + methodName);

        UUID code = UUID.randomUUID();
        System.out.println("生成的uuid是:" + code);


        String serviceCode = "ngmtt_get_roleDataOrStaffInfo";

        String[] str = serviceCode.split("_");
        System.out.println("分割的字符串长度是:" + str.length);
        System.out.println("获取的method是:" + str[str.length - 1]);

        String channel = StringUtils.replace("ord;ams;soa", ";", "_");

        System.out.println("channel的结果是:" + channel);


        int number = 123;
        int count = 5;
        System.out.println("取余的值是" + number % 4);

        System.out.println("验证的值是:" + StringUtils.replace(UUID.randomUUID().toString(), "-", ""));
    }
}
