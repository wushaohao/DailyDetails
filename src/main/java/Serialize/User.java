package Serialize;

import java.io.Serializable;

/**
 * Created by wuhao on 16/12/28.
 */
public class User implements Serializable {
    public static String userName;
    private transient String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
