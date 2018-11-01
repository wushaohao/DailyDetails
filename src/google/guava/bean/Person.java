package google.guava.bean;

import lombok.Data;
import lombok.NonNull;

/**
 * @author:wuhao
 * @description:人物Bean对象
 * @date:18/11/1
 */
@Data
public class Person {
    @NonNull
    int id;
    int age;
    String name;
}
