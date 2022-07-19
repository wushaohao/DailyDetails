package jacksonJson;


import org.codehaus.jackson.map.ObjectMapper;

import java.util.*;

/**
 * @author wuhao
 * @date 16/10/28
 */
public class demo_test2 {

    public static void main(String[] args) throws Exception {

        User user = new User();
        user.setUsername("不良人");
        user.setAge(20);
        user.setAddress("藏兵谷");
        user.setSex("F");

        User user1 = new User();
        user1.setUsername("不良人2");
        user1.setAge(30);
        user1.setAddress("藏兵谷");
        user1.setSex("M");
        List<User> lists = new ArrayList<User>();
        lists.add(user);
        lists.add(user1);

        List<BOSetUseInfo> lists1 = new ArrayList<BOSetUseInfo>();
        for (User users : lists) {
            BOSetUseInfo boSetUseInfo = new BOSetUseInfo();
            Set<String> sets = new HashSet<String>();
            sets.add(users.getUsername());
            boSetUseInfo.setUser(users);
            boSetUseInfo.setSets(sets);
            lists1.add(boSetUseInfo);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(lists1);
        System.out.println("输出的结果是:" + jsonStr);


        Map<String, String> maps = new HashMap<String, String>();
        maps.put("retVal", "Y");
        maps.put("retMsg", "成功");
        maps.put("msg", "测试");

        String retStr = objectMapper.writeValueAsString(maps);
        System.out.println("Map集合测试结果是:" + retStr);
    }
}
