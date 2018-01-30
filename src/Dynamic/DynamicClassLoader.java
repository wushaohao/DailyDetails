package Dynamic;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * 动态加载
 * @author wuhao
 */
public class DynamicClassLoader extends ClassLoader
{
    private static DynamicClassLoader loader = null;
    
    private DynamicClassLoader(ClassLoader parent)
    {
        super(parent);
    }

    public static DynamicClassLoader getLoader(ClassLoader parent) {
        if(null == loader)
        {
            loader = new DynamicClassLoader(parent);
        }
        return loader;
        
    }

    public Class<?> loadClass(String classPath, String className) throws ClassNotFoundException {
        try
        {
            String url = classPathParser(classPath) + classNameParser(className);
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            InputStream input = connection.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int data = input.read();
            while (data != -1)
            {
                buffer.write(data);
                data = input.read();
            }
            input.close();
            byte[] classData = buffer.toByteArray();
            return defineClass(noSuffix(className), classData, 0, classData.length);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String pathParser(String path)
    {
        return path.replaceAll("\\\\", "/");
    }

    private String classPathParser(String path)
    {
        String classPath = pathParser(path);
        if (!classPath.startsWith("file:"))
        {
            classPath = "file:" + classPath;
        }
        if (!classPath.endsWith("/"))
        {
            classPath = classPath + "/";
        }
        return classPath;
    }

    private String classNameParser(String className)
    {
        return className.substring(0, className.lastIndexOf(".")).replaceAll("\\.", "/") + className.substring(className.lastIndexOf("."));
    }

    private String noSuffix(String className)
    {
        return className.substring(0, className.lastIndexOf("."));
    }


    public static Class<?> load(String classPath, String className)
    {
        Class<?> clazz = null;
        try
        {
            clazz = DynamicClassLoader.getLoader(null).loadClass(classPath, className);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return clazz;
    }
    @Override
    public  Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> clazz = null;
        if("xxxx".equals(name))
        {
            String classpath = System.getenv("");
            clazz = load(classpath, name);
        }
        else
        {
            super.loadClass(name);
        }
        return clazz;
    }
}