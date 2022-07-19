package jarParseFiles;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * @author wuhao
 * @date 16/2/16
 */
public class JARReader {

    private static String ENCODE = "utf-8";

    private static final Map ENTRY_CONTENT_MAP = new HashMap();

    public JARReader() {
    }

    public JARReader(String encode) {
        ENCODE = encode;
    }

    /**
     * 读取jar包
     *
     * @param inputStream
     * @throws Exception
     */
    public List<XmlEntry> readXmlEntries(InputStream inputStream) throws Exception {
        return getXmlEntries(this.readJarEntries(inputStream));
    }

    /**
     * 获取所有 Xml JarEntry 对象
     *
     * @return
     */
    public List<XmlEntry> getXmlEntries(List<JarEntry> entries) {
        List<XmlEntry> xmlEntries = new ArrayList<XmlEntry>();
        for (JarEntry entry : entries) {
            System.out.println("文件名称是:" + entry.getName());
            if (entry.getName().toLowerCase().endsWith(".class")) {
                int start = entry.getName().lastIndexOf("/");
                int end = entry.getName().lastIndexOf(".");
                String fileName = entry.getName().substring(start + 1, end);
                if (fileName.endsWith("Service")) {
                    System.out.println("--------获取的服务接口名称是:----------" + fileName);
//                    xmlEntries.add(new XmlEntry(entry));
//                    try {
//                        Class clazz=Thread.currentThread().getContextClassLoader().loadClass(entry.getName());
////                        Class clazz=Class.forName(entry.getName().substring(start+1));
//                        Method[] methods=clazz.getMethods();
//                        for (int i = 0; i < methods.length; i++) {
//                            System.out.println("-*******"+fileName+"*******-");
//                            System.out.println("方法是:"+methods[i]);
//                            System.out.println("-*******"+fileName+"*******-");
//                        }
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                    }
                    xmlEntries.add(new XmlEntry(entry));
                }
            }
        }
        System.out.println("服务接口的数目是:" + xmlEntries.size());
        return xmlEntries;
    }

    /**
     * 字符串方式获取 JarEntry 对象内容
     *
     * @param entry
     * @return
     */
    public String getEntryAsString(JarEntry entry) {
        if (entry == null) {
            return null;
        }
        return (String) ENTRY_CONTENT_MAP.get(entry.getName());
    }

    /**
     * 读取Entry
     *
     * @param in
     * @return
     * @throws IOException
     */
    private List<JarEntry> readJarEntries(InputStream in) throws Exception {
        List<JarEntry> entryList = new ArrayList();
        JarInputStream jarInput = new JarInputStream(in);
        JarEntry entry = jarInput.getNextJarEntry();
        while (entry != null) {
            entryList.add(entry);
            extractEntryContent(jarInput, entry);
            entry = jarInput.getNextJarEntry();
        }
        return entryList;
    }

    /**
     * 抽取并保存Entry对应的流
     *
     * @param in
     * @param entry
     * @throws IOException
     */
    private void extractEntryContent(InputStream in, JarEntry entry) throws Exception {
        if (!ENTRY_CONTENT_MAP.containsKey(entry.getName())) {
            ByteArrayOutputStream _copy = new ByteArrayOutputStream();
            int read = 0;
            int chunk = 0;
            byte[] data = new byte[256];
            while (read < entry.getSize() && -1 != (chunk = in.read(data))) {
                read += data.length;
                _copy.write(data, 0, chunk);
            }
            ENTRY_CONTENT_MAP.put(entry.getName(), getAsString(_copy));
        }
    }

    /**
     * 以字符串方式获取Entry内容
     *
     * @return
     * @throws Exception
     */
    private String getAsString(ByteArrayOutputStream bos) throws Exception {
        ByteArrayInputStream bis = null;
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();
        try {
            bis = new ByteArrayInputStream(bos.toByteArray());
            reader = new BufferedReader(new InputStreamReader(bis, ENCODE));
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (bis != null) {
                bis.close();
            }
            if (bos != null) {
                bos.close();
            }
        }
        return buffer.toString();
    }

    public class XmlEntry {

        private JarEntry entry;

        public XmlEntry(JarEntry entry) {
            this.entry = entry;
        }

        public String getXmlAsString() {
            return getEntryAsString(entry);
        }
    }

    public static void main(String[] args) throws Exception {
        String path = "csfDBOperate-1.0.war";
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        JARReader readClasses = new JARReader();
        List<XmlEntry> xmlEntrys = readClasses.readXmlEntries(inputStream);

        for (int i = 0; i < xmlEntrys.size(); i++) {
            XmlEntry entryTemp = xmlEntrys.get(i);
            System.out.println("名称是:" + entryTemp.entry.getName());
//            Class clazz=Class.forName(entryTemp.entry.getName());
//            Method[] methods=clazz.getMethods();
//            System.out.println("长度是:"+methods.length);
            // 通过读出的xml文件来获取字符缓冲数组
//            System.out.println("entryTemp文件是:" + entryTemp.getXmlAsString());
            ByteArrayInputStream bis = new ByteArrayInputStream(entryTemp.getXmlAsString().getBytes());
        }

    }

}
