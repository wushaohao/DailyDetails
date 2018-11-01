package pluglombok;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * @author:wuhao
 * @description:插件lombok@AllArgsConstructor(初始化所有字段)
 * @date:18/11/1
 */
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PlugLombokDemo5 {
    private int x, y;
    @NonNull
    private String description;
}
