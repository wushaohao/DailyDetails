package Serialize.fst;

import java.io.Serializable;

/**
 * @author:wuhao
 * @description:序列化Bean
 * @date:18/4/17
 */
public class User implements Serializable {
    private String username;
    private int age;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
