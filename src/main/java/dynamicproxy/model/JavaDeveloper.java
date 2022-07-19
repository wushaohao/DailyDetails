package dynamicproxy.model;

/**
 * @author:wuhao
 * @description:Java开发者
 * @date:2020/3/2
 */
public class JavaDeveloper implements Developer {
    private String name;

    public JavaDeveloper(String name) {
        this.name = name;
    }

    @Override
    public void code() {
        System.out.println(this.name + " is coding java");
    }

    @Override
    public void debug() {
        System.out.println(this.name + " is debugging java");
    }
}
