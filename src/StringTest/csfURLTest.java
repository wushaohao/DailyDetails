package StringTest;

import java.util.HashMap;

/**
 * Created by wuhao on 16/12/12.
 */
public class csfURLTest {

    public static void main(String[] args){
//        String url="/busidomain/csf/systemCode/services/serviceCode?url=http://192.168.100.35:9950/ngmttcore/ws/auth/role/roleDataOrStaffInfo&method=get";

        String url="http://192.168.100.35:9950/ngmttcore/ws/auth/role/roleDataOrStaffInfo&method=get";
        if(url != null && (url = url.trim()).length() != 0) {
            String protocol = null;
            String username = null;
            String password = null;
            String host = null;
            int port = 0;
            String path = null;
            HashMap parameters = null;
            int i = url.indexOf("?");
            if(i >= 0) {
                String[] j = url.substring(i + 1).split("\\&");
                parameters = new HashMap();
                String[] arr$ = j;
                int len$ = j.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    String part = arr$[i$];
                    part = part.trim();
                    if(part.length() > 0) {
                        // j1的值是获取＝在当前url中的位置
                        int j1 = part.indexOf(61);
                        System.out.println("j1的值是:"+j1+"\t"+"part:"+part);
                        if(j1 >= 0) {
                            parameters.put(part.substring(0, j1), part.substring(j1 + 1));
                            System.out.println("part.substring(0, j1)的值是:"+part.substring(0, j1));
                            System.out.println("part.substring(j1+1)的值是:"+part.substring(j1+1));
                            // key为url value为url的值
                            System.out.println("j1的值是:"+parameters.put(part.substring(0, j1), part.substring(j1 + 1)));
                        } else {
                            parameters.put(part, part);
                        }
                    }
                }

                url = url.substring(i+1);
                System.out.println("url的值为:"+url);
            }

            i = url.indexOf("://");
            System.out.println("i的值为:"+i);
            if(i >= 0) {
                if(i == 0) {
                    throw new IllegalStateException("url missing protocol: \"" + url + "\"");
                }

                protocol = url.substring(0, i);
                url = url.substring(i + 3);
                System.out.println("protocol协议是:"+protocol+"\t"+"url是:"+url);
            } else {
                i = url.indexOf(":/");
                System.out.println("i的值是:"+i);
                if(i >= 0) {
                    if(i == 0) {
                        throw new IllegalStateException("url missing protocol: \"" + url + "\"");
                    }

                    protocol = url.substring(0, i);
                    url = url.substring(i + 1);
                }
            }

            i = url.indexOf("/");
            System.out.println("i的值是:"+i);
            if(i >= 0) {
                path = url.substring(i + 1);
                url = url.substring(0, i);
                System.out.println("path是:"+path+"\t"+"url是:"+url);
            }

            i = url.indexOf("@");
            if(i >= 0) {
                username = url.substring(0, i);
                int var15 = username.indexOf(":");
                if(var15 >= 0) {
                    password = username.substring(var15 + 1);
                    username = username.substring(0, var15);
                }

                url = url.substring(i + 1);
            }

            i = url.indexOf(":");
            if(i >= 0 && i < url.length() - 1) {
                port = Integer.parseInt(url.substring(i + 1));
                url = url.substring(0, i);
                System.out.println("port:"+port+"\t"+"获取的url是:"+url);
            }

            if(url.length() > 0) {
                host = url;
                System.out.println("获取的url是:"+host);
            }

//            return new URL(protocol, username, password, host, port, path, parameters);
        } else {
            throw new IllegalArgumentException("url == null");
        }
    }
}
