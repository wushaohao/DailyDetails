package annotation.limit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:wuhao
 * @description:测试Controller类
 * @date:2020/5/3
 */
@Controller
public class TestController {
    @RateLimitAspect
    @ResponseBody
    @RequestMapping("/test")
    public String test() {
        return "success";
    }

}
