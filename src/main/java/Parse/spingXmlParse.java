package Parse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.InputStream;

/**
 * Created by wuhao on 17/1/27.
 */
public class spingXmlParse {

    public static JSONArray queryXml() {

        System.out.println("解析开始...");
//		log.info("MyHadler1 queryXml is start..");
        // 创建dom解析工厂
        DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();

        try {
            // 创建dom解析器
            DocumentBuilder dbbuilder = dbfactory.newDocumentBuilder();
            // 文件路径
            String path = "provider.xml";
            InputStream inputStream=Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

            if (inputStream==null){
                throw new Exception("获取不到解析的文件流....");
            }

            // 解析文件
            Document doc = dbbuilder.parse(inputStream);

            JSONArray arr = new JSONArray();
            //获取bean标签集合
            NodeList list = doc.getElementsByTagName("hsf:provider");
            //获取property标签集合
            NodeList proList = doc.getElementsByTagName("property");

            // 解析bean值集合的值
            for (int j = 0; j < list.getLength(); j++) {
                Element el = (Element) list.item(j);
                System.out.println("获取的服务的ID标识是:"+el.getAttribute("id"));
                System.out.println("获取的类型是:"+el.getAttribute("interface"));
            }

            // 解析property集合值 获取服务实现接口
            for (int i = 0; i < proList.getLength(); i++) {
                Element el= (Element) proList.item(i);
                System.out.println("获取到的接口标识是:"+el.getAttribute("name"));
                if ("interfaceName".equalsIgnoreCase(el.getAttribute("name"))){
                    String interfaces= el.getElementsByTagName("value").item(0).getFirstChild().getNodeValue();
                    System.out.println("获取到的服务实现接口是:"+interfaces);
                    int index=interfaces.lastIndexOf(".");
                    System.out.println("服务接口的名称是:"+interfaces.substring(index+1));
                    break;//获取到服务集合 停止解析
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        queryXml();
    }
}
