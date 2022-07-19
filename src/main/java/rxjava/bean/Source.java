package rxjava.bean;

/**
 * @author:wuhao
 * @Description:课程类
 * @Date:18/1/7
 */
public class Source {
    private int sourceId;
    private String name;
    private int score;

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Source(int sourceId, String name, int score) {
        this.sourceId = sourceId;
        this.name = name;
        this.score = score;
    }
}
