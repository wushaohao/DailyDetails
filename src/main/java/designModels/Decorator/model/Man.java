package designModels.Decorator.model;

/**
 * @author:wuhao
 * @description:具体实现类
 * @date:2019/12/31
 */
public class Man implements Person {
    @Override
    public void eat() {
        System.out.println("Man is eating....");
    }
}
