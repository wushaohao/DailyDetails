package jarParseFiles;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * @author wuhao
 */
public class JarFileReader {
    private JarInputStream jarInput;
    private HashMap<String, ByteArrayOutputStream> entriesStreamMap;
    private static List<String> lists;

    public JarFileReader(InputStream in) throws IOException {
        jarInput = new JarInputStream(in);  
        entriesStreamMap = new HashMap<String, ByteArrayOutputStream>();
        lists=new ArrayList<String>();
    }  
      
    public void readEntries() throws IOException {  
        JarEntry entry = jarInput.getNextJarEntry();
        String manifestEntry = null;
        String fileName=null;

        while(entry != null) {
            System.out.println("Entry Name = " + entry.getName());  
            if(entry.getName().endsWith("Service.class")) {
                manifestEntry = entry.getName();
                copyInputStream(jarInput, manifestEntry);
                lists.add(manifestEntry);
            }  
            entry = jarInput.getNextJarEntry();

        }

        System.out.println("接口的数目是:"+lists.size());
    }
      
    public void copyInputStream(InputStream in, String entryName) throws IOException {  
        if(!entriesStreamMap.containsKey(entryName)) {  
            ByteArrayOutputStream _copy = new ByteArrayOutputStream();  
            int read = 0;  
            int chunk = 0;  
            byte[] data = new byte[256];  
            while(-1 != (chunk = in.read(data)))  
            {  
                read += data.length;  
                _copy.write(data, 0, chunk);  
            }  
            entriesStreamMap.put(entryName, _copy);  
        }  
    }  
      
    public InputStream getCopy(String entryName) {  
        ByteArrayOutputStream _copy = entriesStreamMap.get(entryName);  
        return (InputStream)new ByteArrayInputStream(_copy.toByteArray());
    }  
      
    public static void main(String[] args) throws Exception {
        File jarFile = new File("/Users/wuhao/Downloads/downProjects/java_details/src/interfaces.jar");
        try {  
            InputStream in = new BufferedInputStream(new FileInputStream(jarFile));  
            JarFileReader reader = new JarFileReader(in);  
            reader.readEntries();

            for (String fileName:lists) {
                System.out.println("Now!! + get jar entry inputstream again...");
                InputStream inputStream = reader.getCopy(fileName);
                int start=fileName.lastIndexOf("/");
                int end=fileName.lastIndexOf(".");
                String name=fileName.substring(start+1);
                File dirs = new File("./documents/com/cmos/csf/service/");
                if(!dirs.exists()) {
                    dirs.mkdirs();
                }
                FileOutputStream fileOutputStream=new FileOutputStream("./documents/com/cmos/csf/service/"+name);
                int ch = 0;
                try {
                    while((ch=inputStream.read()) != -1){
                        fileOutputStream.write(ch);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally{
                    //关闭输入流等（略）
                    fileOutputStream.close();
                    inputStream.close();
                }

//                URL url = new URL("file:/Users/wuhao/Downloads/downProjects/java_details/src/csfDBOperate-1.0.war!" +
//                        "WEB-INF/classes");
//                URLClassLoader ucl = new URLClassLoader(new URL[] {url});
//                System.out.println(ucl.loadClass("com.cmos.csf.controller.CsfRestfulServiceInfoController"));

                try {
//                    Class clazz=Class.forName(name);
                    Class clazz =Thread.currentThread().getContextClassLoader().loadClass("com.cmos.csf.service."+name.replaceAll("[.]class",""));
                    Method[] methods=clazz.getMethods();
                    for (int i = 0; i < methods.length; i++) {
                        System.out.println("-*******"+name+"*******-");
                        System.out.println("方法是:"+methods[i]);
                        System.out.println("-*******"+name+"*******-");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
      
} 