package google.guava.fileoperate;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author:wuhao
 * @description:文件操作
 * @date:18/11/1
 */
public class FileOperate {
    public static void main(String[] args) {
        File file = new File("/test.txt");
        List<String> lists = null;
        try {
            lists = Files.readLines(file, Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Files.copy(from,to); //复制文件
//        Files.deleteDirectoryContents(File directory); //删除文件夹下的内容(包括文件与子文件夹)
//        Files.deleteRecursively(File file); //删除文件或者文件夹
//        Files.move(File from, File to); //移动文件
//        URL url = Resources.getResource("abc.xml"); //获取classpath根下的abc.xml文件url

    }
}
