package annotation.model;


/**
 *
 * @author wuhao
 * @date 17/3/12
 * 使用自定义注解：
 * 使用注解的语法：
 * @<注解名>(<成员名1>=<成员值1>,<成员名1>=<成员值1>,…)
 */
public class demo1 {

    @Description(desc="red",author="wuhao")
    public String getColor(){
        System.out.println("I want a colorful life!");
        return "I want a colorful life!";
    }

    public static void main(String[] args) {
        demo1 d=new demo1();
        d.getColor();
    }
}
