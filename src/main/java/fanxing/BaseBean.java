package fanxing;

import lombok.Data;

/**
 * @author:wuhao
 * @description:基础类型
 * @date:2019/12/17
 */
@Data
public class BaseBean<T> {
    T value;
}
