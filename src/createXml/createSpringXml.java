package createXml;

import java.io.*;
import java.util.Map;

/**
 * Created by wuhao on 17/2/3.
 */
public class createSpringXml {

    public static StringBuffer buildXmlHead(Map<String,String> maps,String centerName){
//        String id="helloWorldServiceImpl";
//        String className="com.taobao.hsf.test.service.HelloWorldServiceImpl";
//
//        String id2="helloWorldServiceProvider";
//        String className2="com.taobao.hsf.app.spring.util.HSFSpringProviderBean";
        String init="init";
//
//        String propertyName="target";
//
//        String propertyName2="serviceName";
//
//        String valueName="HellWorldService";
        //报文头
        StringBuffer metaBuf = new StringBuffer("<?xml version=\"1.0\" encoding=\"" + FileTypeConstant.META_ENCODING + "\"?>\n");
        metaBuf.append("<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.5.xsd\">");

//        metaBuf.append("\n");
//        metaBuf.append("<bean id=\"").append(id).append("\" class=\"").append(className).append("\"")
//                .append("/>");

        metaBuf.append("\n");
        for (String key:maps.keySet()) {
            metaBuf.append("<hsf:provider id=\"").append(key).append("\" interface=\"").append(maps.get(key)).append("\"").append(" init-method=\"").append(init).append("\"").append(">");
            metaBuf.append("\n");
            metaBuf.append("</hsf:provider>").append("\n");
        }

//        metaBuf.append("\n");
//        metaBuf.append("<property name=\"").append(propertyName).append("\"").append(">").append("\n");
//        metaBuf.append("<ref bean=\"").append(id).append("\"").append("/>").append("\n");
//        metaBuf.append("</property>");
//
//        metaBuf.append("\n");
//        metaBuf.append("<property name=\"").append(propertyName2).append("\"").append(">").append("\n");
//        metaBuf.append("<value>").append(valueName).append("</value>").append("\n");
//        metaBuf.append("</property>").append("\n");


        metaBuf.append("</beans>");

        System.out.println("输出的报文是:"+"\n"+metaBuf.toString());
        string2File(metaBuf.toString(),"./documents/"+centerName+".xml");

        return metaBuf;
    }

    public static boolean string2File(String res, String filePath) {
        boolean flag = true;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            File distFile = new File(filePath);
            if (!distFile.getParentFile().exists()) distFile.getParentFile().mkdirs();
            bufferedReader = new BufferedReader(new StringReader(res));
            bufferedWriter = new BufferedWriter(new FileWriter(distFile));
            char buf[] = new char[1024];         //字符缓冲区
            int len;
            while ((len = bufferedReader.read(buf)) != -1) {
                bufferedWriter.write(buf, 0, len);
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
            return flag;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    public static void main(String[] args) {
//        StringBuffer sb = buildXmlHead();
    }
}
