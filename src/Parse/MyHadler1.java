package Parse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class MyHadler1 {

//	private static SrvLog log = SrvLog.createLogger(MyHandler.class);

	public static JSONArray queryXml() {

//		log.info("MyHadler1 queryXml is start..");
		// 创建dom解析工厂
		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();

		try {
			// 创建dom解析器
			DocumentBuilder dbbuilder = dbfactory.newDocumentBuilder();
			// 文件路径
			String path = "menu.xml";
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
			// 解析文件
			Document doc = dbbuilder.parse(is);

			// 获取目录的总数
			NodeList menucount = doc.getElementsByTagName("MenuCounts");
			int count = Integer.parseInt(menucount.item(0).getFirstChild().getNodeValue());
//			log.info("总共有的目录总数是:" + count);

			JSONArray arr = new JSONArray();
			for (int i = 1; i <= count; i++) {
				NodeList list = doc.getElementsByTagName("menu" + i);
				JSONObject objF = new JSONObject();
//				log.info("当前的是第" + i + "目录");
				for (int j = 0; j < list.getLength(); j++) {
					Element el = (Element) list.item(j);
//					log.info(el.getElementsByTagName("funcId").item(0).getFirstChild().getNodeValue());
					objF.put("funcID", el.getElementsByTagName("funcId").item(0).getFirstChild().getNodeValue());

//					log.info(el.getElementsByTagName("funcName").item(0).getFirstChild().getNodeValue());
					objF.put("funcName", el.getElementsByTagName("funcName").item(0).getFirstChild().getNodeValue());

//					log.info(el.getElementsByTagName("parentId").item(0).getFirstChild().getNodeValue());
					objF.put("parentId", el.getElementsByTagName("parentId").item(0).getFirstChild().getNodeValue());

//					log.info(el.getElementsByTagName("viewName").item(0).getFirstChild().getNodeValue());
					objF.put("viewName", el.getElementsByTagName("viewName").item(0).getFirstChild().getNodeValue());
				}

				NodeList childlist = doc.getElementsByTagName("childrenmenu" + i);
//				log.info("当前的子目录是第" + i + "目录");
				for (int ii = 0; ii < childlist.getLength(); ii++) {
					Element el = (Element) childlist.item(ii);
					JSONObject objC = new JSONObject();
//					log.info(el.getElementsByTagName("funcId").item(0).getFirstChild().getNodeValue());
					objC.put("funcID", el.getElementsByTagName("funcId").item(0).getFirstChild().getNodeValue());

//					log.info(el.getElementsByTagName("funcName").item(0).getFirstChild().getNodeValue());
					objC.put("funcName", el.getElementsByTagName("funcName").item(0).getFirstChild().getNodeValue());

//					log.info(el.getElementsByTagName("parentId").item(0).getFirstChild().getNodeValue());
					objC.put("parentId", el.getElementsByTagName("parentId").item(0).getFirstChild().getNodeValue());

//					log.info(el.getElementsByTagName("viewName").item(0).getFirstChild().getNodeValue());
					objC.put("viewName", el.getElementsByTagName("viewName").item(0).getFirstChild().getNodeValue());

					objF.put("children", objC);
				}
				arr.put(objF);
			}

//			log.info("最终的数组串是:" + arr.toString());
			return arr;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
