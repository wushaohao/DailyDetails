package annotation.model;

import java.lang.annotation.*;

/**
 * Created by wuhao on 17/3/12.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Description2 {
    String value();
}
