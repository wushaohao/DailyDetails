package rxjava.bean;

/**
 * @author:wuhao
 * @Description:User类
 * @Date:18/1/6
 */
public class User {
    private String userName;
    private String address;
    private int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
