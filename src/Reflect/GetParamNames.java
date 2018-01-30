package Reflect;

import java.lang.reflect.Method;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;    
    
public class GetParamNames {    
    public static void main(String[] args) throws Exception {
        LocalVariableTableParameterNameDiscoverer u =     
            new LocalVariableTableParameterNameDiscoverer();
        Class clazz=Class.forName("createXml.createSpringXml");
        Method[] methods=clazz.getDeclaredMethods();

        String[] params = u.getParameterNames(methods[0]);    
        for (int i = 0; i < params.length; i++) {    
            System.out.println(params[i]);    
        }    
    }    
}