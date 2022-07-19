package designModels.Decorator.model;

import lombok.Data;

/**
 * @author:wuhao
 * @description:抽象装饰者
 * @date:2019/12/31
 */
@Data
public abstract class Decorator implements Person {
    protected Person person;

    @Override
    public void eat() {
        person.eat();
    }
}
