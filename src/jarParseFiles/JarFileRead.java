package jarParseFiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author wuhao
 */
public class JarFileRead {

    public static void main (String args[]) throws IOException {

        String jarPath="csfDBOperate-1.0.war";
        JarFile jarFile = new JarFile(jarPath);
        Enumeration enu = jarFile.entries();
        while (enu.hasMoreElements()) {  
         JarEntry entry = (JarEntry)enu.nextElement();
            String name = entry.getName();  
            System.out.println(name);
            if(entry.getName().toLowerCase().endsWith(".class")){
                int start=entry.getName().lastIndexOf("/");
                int end=entry.getName().lastIndexOf(".");
                String fileName=entry.getName().substring(start+1,end);
                if (fileName.endsWith("Service")) {
                    InputStream input = jarFile.getInputStream(entry);
                    process(input);
                }
            }  
        }         
        jarFile.close();  
    }

    private static void process(InputStream input)  
        throws IOException {  
        InputStreamReader isr = new InputStreamReader(input);
        BufferedReader reader = new BufferedReader(isr);
        String line;  
        while ((line = reader.readLine()) != null) {  
            System.out.println(line);  
        }  
        reader.close();  
    }  
}  