package rxjava.bean;

import java.util.List;

/**
 * @author:wuhao
 * @Description:学生类
 * @Date:18/1/7
 */
public class Student {
    private String name;
    private int id;
    private List<Source> mSource;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Source> getmSource() {
        return mSource;
    }

    public void setmSource(List<Source> mSource) {
        this.mSource = mSource;
    }

    public Student(String name, int id, List<Source> mSource) {
        this.name = name;
        this.id = id;
        this.mSource = mSource;
    }
}
