package Netty.callback;

/**
 * @author:wuhao
 * @description:
 * @date:18/7/12
 */
public class Data {

    private int m;
    private int n;

    public Data(int m, int n) {
        this.m = m;
        this.n = n;
    }

    @Override
    public String toString() {
        return "Data{" +
                "m=" + m +
                ", n=" + n +
                '}';
    }
}
