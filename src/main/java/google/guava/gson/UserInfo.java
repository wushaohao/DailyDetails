package google.guava.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import lombok.Data;

/**
 * @author:wuhao
 * @description:java单体类
 * @date:2019-08-14
 */
@Data
public class UserInfo {
    @Expose
    private String name;
    //  @SerializedName("MyAge")
    @Since(3.2)
    private int age;
    @Until(3.2)
    private String sex;
}
