package StringTest;

/**
 * Created by wuhao on 17/2/18.
 */
public class splitTest {

    public static void main(String[] args) {
        String str="ALL|ORD";

        String[] name=str.split("\\|");

        System.out.println(name[1]);


        String packageName="com.chinaunicom.number.busi.bo.NumberCardPreplanFlagBO";
        int index=packageName.lastIndexOf(".");
        String path=packageName.substring(0,index);
        System.out.println("包名是:"+path);

        String oldStr="socket://0.0.0.0:38413?";
        System.out.println("截取的是"+oldStr.indexOf("0.0.0.0:"));
        System.out.println("截取的是:"+oldStr.substring(0,oldStr.indexOf("0.0.0.0:")+8));
        System.out.println("截取的是:"+oldStr.substring(oldStr.indexOf("?")));
        System.out.println("新字符串是:"+oldStr.substring(0,oldStr.indexOf("0.0.0.0:")+8)+"30000"+oldStr.substring(oldStr.indexOf("?")));
    }
}
