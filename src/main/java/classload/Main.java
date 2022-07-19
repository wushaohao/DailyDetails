package classload;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import static com.ai.appframe2.service.proxy.ProxyFactory.getClassLoader;

/**
 * @author:wuhao
 * @description:程序加载文件
 * @date:18/8/8
 */
public class Main {
    public URL getResource(String name) {
        name = resolveName(name);
        ClassLoader cl = getClassLoader();
        if (cl == null) {
            //
            return ClassLoader.getSystemResource(name);
        }
        return cl.getResource(name);
    }

    private String resolveName(String name) {
        if (name == null) {
            return name;
        }

        if (!name.startsWith("/")) {
//            Class<?> c = this;
//            while (c.isArray()) {
//                c = c.getComponentType();
//            }
//            String baseName = c.getName();
//            int index = baseName.lastIndexOf(".");
//            if (index != -1) {
//                name = baseName.substring(0, index).replace(".", "/") + "/" + name;
//            } else {
//                name = name.substring(1);
//            }
        }
        return name;
    }

    public static void main(String[] args) {
        String absoultePath = "/test.properties";
        String relativePath = "test.properties";
        InputStream absoluteIn = Main.class.getResourceAsStream(absoultePath);
        InputStream relativeIn = Main.class.getResourceAsStream(relativePath);
        //假设编译后的文件按照包结构放置在bin目录（eclipse默认）
        //传绝对路径加载的是相对于bin目录的文件:bin/test.properties
        Properties absoluteProp = new Properties();
        try {
            absoluteProp.load(absoluteIn);
            System.out.println(absoluteProp.getProperty("path"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //传相对路径加载的是相对于Main.class所在目录的文件:bin/grucee/test/test.properties
        Properties relativeProp = new Properties();
        try {
            relativeProp.load(relativeIn);
            System.out.println(relativeProp.getProperty("path"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
