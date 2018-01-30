package jarParseFiles;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 *
 * @author wuhao
 * @date 17/2/1
 */
public class JarReaderClasses {

    private static String ENCODE = "utf-8";

    private static final Map ENTRY_CONTENT_MAP = new HashMap();

    public JarReaderClasses() {
    }

    public JarReaderClasses(String encode) {
        this.ENCODE = encode;
    }

    /**
     * 读取jar包
     * @param inputStream
     * @throws Exception
     */
    public List<FileEntry> readFileEntries(InputStream inputStream) throws Exception {
        return getXmlEntries(this.readJarEntries(inputStream));
    }

    /**
     * 获取所有 Xml JarEntry 对象
     * @return
     */
    public List<FileEntry> getXmlEntries(List<JarEntry> entries) {
        List<FileEntry> xmlEntries = new ArrayList<FileEntry>();
        for(JarEntry entry : entries)
            if (entry.isDirectory()) {
                Object entry1 = entry;
                File entry11 = (File) entry1;
                File [] sFiles=entry11.listFiles();
                for(int i=0;sFiles!=null&&i<sFiles.length;i++){
                    getChildFiles(sFiles[i],xmlEntries);
                }
            } else {
                String fileName = entry.getName();
                if (fileName.endsWith("Service.java")) {
                    xmlEntries.add(new FileEntry(entry));
                }
            }
        return xmlEntries;
    }

    /**
     * 字符串方式获取 JarEntry 对象内容
     * @param entry
     * @return
     */
    public String getEntryAsString(JarEntry entry) {
        if(entry == null) {
            return null;
        }
        return (String) ENTRY_CONTENT_MAP.get(entry.getName());
    }

    /**
     * 读取Entry
     * @param in
     * @return
     * @throws IOException
     */
    private List<JarEntry> readJarEntries(InputStream in) throws Exception {
        List<JarEntry> entryList = new ArrayList();
        JarInputStream jarInput = new JarInputStream(in);
        JarEntry entry = jarInput.getNextJarEntry();
        while(entry != null) {
            entryList.add(entry);
            extractEntryContent(jarInput, entry);
            entry = jarInput.getNextJarEntry();
        }
        return entryList;
    }

    /**
     * 抽取并保存Entry对应的流
     * @param in
     * @param entry
     * @throws IOException
     */
    private void extractEntryContent(InputStream in, JarEntry entry) throws Exception {
        if(!ENTRY_CONTENT_MAP.containsKey(entry.getName())) {
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
            while ((line = reader.readLine()) != null){
                buffer.append(line);
            }
        } finally {
            if(reader != null) {
                reader.close();
            }
            if(bis != null) {
                bis.close();
            }
            if(bos != null) {
                bos.close();
            }
        }
        return buffer.toString();
    }

    public class XmlEntry  {

        private JarEntry entry;

        public XmlEntry(JarEntry entry) {
            this.entry = entry;
        }

        public String getXmlAsString() {
            return getEntryAsString(entry);
        }
    }

    public class FileEntry  {

        private JarEntry entry;

        public FileEntry(JarEntry entry) {
            this.entry = entry;
        }

        public String getFileAsString() {
            return getEntryAsString(entry);
        }
    }

    private static void getChildFiles(File input, List<FileEntry> inputList){
        File [] sFiles=input.listFiles();
        for(int i=0;sFiles!=null&&i<sFiles.length;i++){
            getChildFiles(sFiles[i],inputList);
        }

    }

    public static void main(String[] args) throws Exception {
        String path="csfDBOperate-1.0.war";
        InputStream inputStream=Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        JarReaderClasses  readClasses=new JarReaderClasses();
        readClasses.readFileEntries(inputStream);
    }
}
