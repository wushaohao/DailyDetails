package StringTest;

import java.util.regex.Pattern;

/**
 * 表达式集合
 *
 * @author Grucee
 */
public interface Patterns {

    public interface TreeNodePatterns {
        //第一个分组是路径，第二个分组是配置的表达式  feeinfo{upperFirst(*);lowerFirst(*);convert(key1=key2,key3=key4)}
        Pattern FUNCTION_PATTERN = Pattern.compile("(.*)\\{(.+)\\}");
        //参数获取  upperFirst(*)，第一个分组是函数名，第二个分组是函数参数
        Pattern PARAMS_PATTERN = Pattern.compile("(.+)\\((.+)\\)");
    }

}
