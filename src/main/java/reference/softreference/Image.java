package reference.softreference;

import java.util.Arrays;

/**
 * @author:wuhao
 * @description:图片
 * @date:18/8/7
 */
public class Image {
    private byte[][] buffer;
    private int id;

    public Image(int id, byte[][] buffer) {
        this.buffer = buffer;
        this.id = id;
    }

    public byte[][] getBuffer() {
        return buffer;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Image{" +
                "buffer=" + Arrays.toString(buffer) +
                ", id=" + id +
                '}';
    }
}
