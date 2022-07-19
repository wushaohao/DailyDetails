package Reflect;

import java.lang.reflect.Method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
public class MethodUtil {
  
    public static String[] getAllParamaterName(Method method)  
        throws NotFoundException {  
    Class<?> clazz = method.getDeclaringClass();  
    ClassPool pool = ClassPool.getDefault();  
    CtClass clz = pool.get(clazz.getName());  
    CtClass[] params = new CtClass[method.getParameterTypes().length];  
    for (int i = 0; i < method.getParameterTypes().length; i++) {  
        params[i] = pool.getCtClass(method.getParameterTypes()[i].getName());  
    }  
    CtMethod cm = clz.getDeclaredMethod(method.getName(), params);  
    MethodInfo methodInfo = cm.getMethodInfo();  
    CodeAttribute codeAttribute = methodInfo.getCodeAttribute();  
    LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute  
        .getAttribute(LocalVariableAttribute.tag);  
    int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;  
    String[] paramNames = new String[cm.getParameterTypes().length];  
    for (int i = 0; i < paramNames.length; i++) {  
        paramNames[i] = attr.variableName(i + pos);  
    }
        System.out.println(paramNames.length);
    return paramNames;  
    }


    public static void main(String[] args) {
        try {
            Class clazz=Class.forName("Serialize.ExternalizableTest");
            Method[] methods=clazz.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                String[] name=getAllParamaterName(methods[i]);
                System.out.println("类名是:"+clazz.getName());
                System.out.println("方法名是:"+methods[i].getName());
                for (int j = 0; j < name.length; j++) {
                    System.out.println("参数的名称是:"+name[j]);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}