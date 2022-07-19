package pluglombok;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;

import java.util.Date;
import java.util.Set;

/**
 * @author:wuhao
 * @description:lombok插件@Data使用
 * @date:18/10/31
 */
@Data
@Builder
public class PlugLombokDemo3 {
    @NonNull
    private String id;
    private String name;
    private boolean active;
    private Date createTime;

    @Singular
    private Set<String> occupations;

    public static void main(String[] args) {
        PlugLombokDemo3 plugLombokDemo3 = PlugLombokDemo3.builder().active(true).name("name").createTime(new Date()).occupation("1").occupation("2").build();
        //Assert.assertEquals(2, plugLombokDemo3.getOccupations().size());
    }
}
