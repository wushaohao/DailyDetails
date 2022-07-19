package Parse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by wuhao on 16/10/20.
 */
public class SaxParse {


//    public   static void main(String[] args){
//        SAXReader saxReader = new SAXReader();
//
//        System.out.println("GetXml2TreeJson\n");
//        System.out.println("");
//
////		strXml = new String(strXml.getBytes("gbk"),"utf-8");
////		InputStream in =  new ByteArrayInputStream(strXml.getBytes("utf-8"));
//        InputStream in =  new ByteArrayInputStream(strXml.getBytes("GBK"));
//
//
//        //Document xmlDoc = saxReader.read(new File("E:/testNanning/0317/srvA.xml"));
//        Document xmlDoc = null;
//        try {
//            xmlDoc = saxReader.read(in);
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//        StringBuilder result = new StringBuilder();
//        StringBuilder sb = new StringBuilder();
//
//        Element oNode = xmlDoc.getRootElement();
//        if (oNode.elements() == null)
//            result.append("[]");
//        else {
//            List<Element> oList = oNode.elements();
//            if(oList!=null&&oList.size()>0){
//                result.append("[");
//                result.append("{\"id\":\"" + srv_code + "\",\"text\":\"" + srv_code + "\",\"state\":\"open\"");
//                result.append(",\"children\":[");
//                for (Element Node : oList) {
//                    sb = getJsonByNode(Node, sb);
//
//                }
//                result.append(sb);
//                result.append("]}");
//                result.append("]");
//            }
//
//        }
//    }
}
