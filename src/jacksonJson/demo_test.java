package jacksonJson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author wuhao
 * 
 * 2016年10月27日
 */
public class demo_test {
	public static void main(String[] args) throws Exception{
		ObjectMapper maps=new ObjectMapper();
		User user=new User();
		user.setUsername("不良人");
		user.setAge(20);
		user.setAddress("藏兵谷");
		user.setSex("F");
		
		User user1=new User();
		user1.setUsername("不良人2");
		user1.setAge(30);
		user1.setAddress("藏兵谷");
		user1.setSex("M");
		
		List<User> lists=new ArrayList<User>();
		lists.add(user);
		lists.add(user1);
		
		String jsonStr;

		jsonStr = maps.writeValueAsString(lists);
		System.out.println("画江湖"+jsonStr);



		List<String> lists2=new ArrayList<String>();
        lists2.add("不良人");
		lists2.add("通文馆");
		lists2.add("幻音坊");
		lists2.add("玄冥教");

		List<BOCsfRouterInfo> boLists=new ArrayList<BOCsfRouterInfo>();

		for(String str:lists2){
			BOCsfRouterInfo boCsfRouterInfo=new BOCsfRouterInfo();
			boCsfRouterInfo.setNodeName(str);
			boLists.add(boCsfRouterInfo);
		}

		String jsonStr2=maps.writeValueAsString(boLists);
		System.out.println("画江湖2"+jsonStr2);
	}



}
