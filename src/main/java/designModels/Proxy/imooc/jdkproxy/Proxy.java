package designModels.Proxy.imooc.jdkproxy;


import org.apache.commons.io.FileUtils;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author:wuhao
 * @description:模拟jdk动态代理原理
 * @date:18/8/16
 */
public class Proxy {

    public static Object newProxyInstance(Class interfaces) {
        // 换行符
        String rt = "\\r\\n";
        //
        String str = "";

        String methodStr = "";
        for (Method m : interfaces.getMethods()) {

        }

        // 产生java代理类的java文件
        String fileName = System.getProperty("user.dir") + "/bin/com/designModels/Proxy/imooc/jdkproxy/$Proxy0.java";
        System.out.println("当前文件路径:" + fileName);
        File file = new File(fileName);
        try {
            FileUtils.writeStringToFile(file, str, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 编译：
         *    拿到编译器
         */
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        // 文件管理者
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        // 获取文件
        Iterable units = fileManager.getJavaFileObjects(fileName);
        // 编译任务
        JavaCompiler.CompilationTask task =
                compiler.getTask(null, fileManager, null, null, null, units);
        // 进行编译
        task.call();
        try {
            fileManager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //load到内存中
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        try {
            Class c = loader.loadClass("");
            System.out.println("" + c.getName());

            Constructor ctr = c.getConstructor(interfaces);
//            return  ctr.newInstance(new ...);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;


    }

}
