package Parse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.net.URL;
import java.util.*;

public class MapTools {
   
//	public static List<Map<String, Object>> parseJSON2List(String jsonStr){
//        JSONArray jsonArr = JSONArray.fromObject(jsonStr);
//		System.out.println("parseJSON2List--->"+jsonArr);
//		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//		Iterator<JSONObject> it = jsonArr.iterator();
//		while(it.hasNext()){
//			JSONObject json2 = it.next();
//			System.out.println("json2--->"+json2.toString());
//			list.add(parseJSON2Map(json2.toString()));
////			list.add(toHashMap(json2.toString()));
//
//		}
//		return list;
//	}
//
//
//	public static Map<String, Object> parseJSON2Map(String jsonStr) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		//最外层解析
//		JSONObject json = JSONObject.fromObject(jsonStr);
//		System.out.println("parseJSON2Map------>"+json);
//		for(Object k : json.keySet()) {
//			Object v = json.get(k);
//			//如果内层还是数组的话，继续解析
//			if (v instanceof JSONArray) {
//				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//				Iterator<JSONObject> it = ((JSONArray) v).iterator();
//				while (it.hasNext()) {
//					JSONObject json2 = it.next();
//					list.add(parseJSON2Map(json2.toString()));
//				}
//				map.put(k.toString(), list);
//			} else {
//				map.put(k.toString(), v);
//			}
//		}
//
//		return map;
//	}
//
//
//	public static List<Map<String, Object>> getListByUrl(String url){
//		try {
//			//通过HTTP获取JSON数据
//			InputStream in = new URL(url).openStream();
//			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//			StringBuilder sb = new StringBuilder();
//			String line;
//			while((line=reader.readLine())!=null){
//				sb.append(line);
//			}
//			return parseJSON2List(sb.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
	public static Map<String, Object> toHashMap(String jsonStr)
	{
		Map<String, Object> data = new HashMap<String, Object>();
		// 将json字符串转换成jsonObject
		JSONObject json = JSONObject.fromObject(jsonStr);
		System.out.println("toHashMap----->"+json);
		Iterator it = json.keys();
		// 遍历jsonObject数据，添加到Map对象
		while (it.hasNext())
		{
//			String key = String.valueOf(it.next());
//			String value = (String) json.get(key);
            String key = String.valueOf(it.next());
            Object value = json.get(key);
            if (value.toString().startsWith("[")){

            }
			data.put(key, value);
		}
		return data;
	}
//
//	public static Map<String, Object> getMapByUrl(String url){
//		try {
//			//通过HTTP获取JSON数据
//			InputStream in = new URL(url).openStream();
//			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//			StringBuilder sb = new StringBuilder();
//			String line;
//			while((line=reader.readLine())!=null){
//				sb.append(line);
//			}
//			return parseJSON2Map(sb.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//
//	//test
	public static void main(String[] args) {
////		String url = "http://...";
////		List<Map<String,Object>> list = getListByUrl(url);
//
//		//JSON 字符串转Map
//		String Jsonstr="{serviceCode:'code1',serviceName:'name',applySystemCode:'sysCode'},{serviceCode:'code2',serviceName:'name2',applySystemCode:'sysCode2'},{serviceCode:'code3',serviceName:'name3',applySystemCode:'sysCode3'}";
//		Map<String,Object> maps=MapTools.parseJSON2Map(Jsonstr);
//
//		for (String keys:maps.keySet()){
//			System.out.println(keys+"--------->"+maps.get(keys));;
//		}
//
//		System.out.println(maps.size());
//
//		// JSON字符串转List
//		String Jsonstr2="[{serviceCode:'code1',serviceName:'name',applySystemCode:'sysCode'},{serviceCode:'code2',serviceName:'name2',applySystemCode:'sysCode2'},{serviceCode:'code3',serviceName:'name3',applySystemCode:'sysCode3'}]";
//
//		List<Map<String, Object>> lists=parseJSON2List(Jsonstr2);
//
//		for (Map<String,Object> maps2:lists) {
//			for (String key:maps2.keySet()){
//				System.out.println(key+":"+maps2.get(key));
//			}
//			System.out.println();
//		}
//
//		//JSON字符串转Map
//		String Jsonstr1="{serviceCode:'code1',serviceName:'name',applySystemCode:'sysCode'},{serviceCode:'code2',serviceName:'name2',applySystemCode:'sysCode2'},{serviceCode:'code3',serviceName:'name3',applySystemCode:'sysCode3'}";
        String Jsonstr1="{'offerId':'1000','businessId':'2000','roleIds':'[{1l,2l}]','prodSpecId':'200'}";
        Map<String, Object> maps1=MapTools.toHashMap(Jsonstr1);
		for (String keys:maps1.keySet()){
			System.out.println(keys+"--------->"+maps1.get(keys));;
		}

		System.out.println(maps1.size());

        String str="[1,2]";
        int begin=str.indexOf("[");
        int end=str.indexOf("]");
        String results=str.substring(begin+1,end);
        String[] temp=results.split(",");
        Object[] arr=new Object[temp.length];
        for (int i = 0; i < temp.length; i++) {
            arr[i]=temp[i];
            System.out.println("数组的值是:"+arr[i]);
        }

        System.out.println(arr.length);

        String testStr="{'offerId':{'key':'value','key2':'value2'}}";
        Map maps3=MapTools.toHashMap(testStr);
        Map maps4=new HashMap<>();
        for (Object keys:maps3.keySet()){
            System.out.println(keys+"--------->"+maps3.get(keys));
            Object tempss= maps3.get(keys);
            maps4=MapTools.toHashMap(tempss.toString());
        }

        System.out.println("maps4的大小是:"+maps4.size());

        for (Object sss:maps4.keySet()) {
            System.out.println(sss+"~~~~~~~~"+maps4.get(sss));
        }

        String Jsonstr2="{'beanId':{'paramKey':'key','paramType':'type'}}";
        Map<String, Object> maps2=MapTools.toHashMap(Jsonstr2);
        BOCsfSrvServiceParamBean bean=null;
        for (String keys:maps2.keySet()){
            System.out.println(keys+"--------->"+maps2.get(keys));
            JSONObject obj=JSONObject.fromString(maps2.get(keys).toString());
            bean= (BOCsfSrvServiceParamBean) JSONObject.toBean(obj,BOCsfSrvServiceParamBean.class);
        }

        System.out.println(bean.getParamKey());
    }
}