package stream.parameternames;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author:wuhao
 * @description:获取参数名称(以前是对字节码的操作)
 * @date:18/12/3
 */
public class ParameterNames {
    /**
     * 为了在运行时获得Java程序中方法的参数名称，老一辈的Java程序员必须使用不同方法，例如Paranamer liberary。Java 8终于将这个特性规范化，
     * 在语言层面（使用反射API和Parameter.getName()方法）和字节码层面（使用新的javac编译器以及-parameters参数）提供支持
     *
     * @param name
     * @param age
     * @param sex
     * @return
     */
    public String getName(String name, String age, String sex) {
        return "name";
    }

    /**
     * 在Java 8中这个特性是默认关闭的，因此如果不带-parameters参数编译上述代码并运行，则会输出如下结果:arg0
     * 如果带-parameters参数，则会输出如下结果（正确的结果）：args
     * ps:在idea中进行设置-parameters参数时是在:
     * 1.File->Settings->Build,Execution,Deployment->Compiler->Java Compiler
     * 2.在 Additional command line parameters: 后面填上 -parameters
     * 3.Rebuild Project
     * 如果你使用Maven进行项目管理，则可以在maven-compiler-plugin编译器的配置项中配置-parameters参数：
     * <plugin>
     * <groupId>org.apache.maven.plugins</groupId>
     * <artifactId>maven-compiler-plugin</artifactId>
     * <version>3.1</version>
     * <configuration>
     * <compilerArgument>-parameters</compilerArgument>
     * <source>1.8</source>
     * <target>1.8</target>
     * </configuration>
     * </plugin>
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            Method method = ParameterNames.class.getMethod("getName", String.class, String.class, String.class);
            for (final Parameter parameter : method.getParameters()) {
                System.out.println("Parameter:" + parameter.getName());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
