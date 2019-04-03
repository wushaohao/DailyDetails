package alibaba;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

/**
 * @author:wuhao
 * @description:Ql解析
 * @date:18/11/27
 */
public class QlExpress_Demo {
    public static void main(String[] args) {
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        // 字符串需要转义
        String express = "params = NewMap(\"low\":\"tplCode1\",\"common\":\"tplCode2\"); if(params.get(\"common\")!=null && params.get(\"common\")!='') then {return params.get(\"common\");} else {return null;}";
//        String express = "params = NewMap(low:tplCode1,common:tplCode2); if(params.get(common)!=null && params.get(common)!='') then {return params.get(common);} else {return null;}";
        // 不能使用StringUtils工具类
//        String express = "params = NewMap(\"low\":\"tplCode1\",\"common\":\"tplCode2\"); if(StringUtils.isNotBlank(params.get(\"common\"))) then {return params.get(\"common\");} else {return null;}";

        ExpressRunner expressRunner = new ExpressRunner();
        try {
            Object r = expressRunner.execute(express, context, null, false, false);
            System.out.println("解析出的模版是:" + r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
