package interviews;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author:wuhao
 * @description:a:3,b:5,c:2@a:1,b:2
 * @date:2020/4/14
 */
public class Test2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        if (null != str && !"".equals(str)) {
            Map<String, String> results = get(str);
            StringBuffer sb = new StringBuffer();
            for (String key : results.keySet()) {
                sb.append(key + ":" + results.get(key) + ",");
            }
            System.out.print(sb.toString().substring(0, sb.toString().length() - 1));
        }
    }

    public static Map<String, String> get(String str) {
        Map<String, String> results = new HashMap<>();
        String[] arrays = str.split(",");
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].contains("@")) {
                String[] sonA = arrays[i].split("@");
                int m = sonA[0].indexOf(":");
                int n = sonA[1].indexOf(":");
                String keyA = sonA[0].substring(0, m);
                String valueA = sonA[0].substring(m + 1, sonA[0].length());
                results.put(keyA, valueA);

                String keyB = sonA[1].substring(0, n);
                String valueB = sonA[1].substring(n + 1, sonA[1].length());

                if (results.containsKey(keyB)) {
                    int value = Math.abs(Integer.valueOf(valueB) - Integer.valueOf(results.get(keyB)));
                    results.put(keyB, value + "");
                } else {
                    results.put(keyB, valueB);
                }
            } else {
                int m = arrays[i].indexOf(":");
                String key = arrays[i].substring(0, m);
                String value = arrays[i].substring(m + 1, arrays[i].length());

                if (results.containsKey(key)) {
                    int temp = Math.abs(Integer.valueOf(value) - Integer.valueOf(results.get(key)));
                    results.put(key, temp + "");
                } else {
                    results.put(key, value);
                }
            }
        }
        return results;

    }
}
