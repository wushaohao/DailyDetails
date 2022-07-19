package pluglombok;

import lombok.Setter;
import lombok.ToString;

/**
 * @author:wuhao
 * @description:插件lombok的ToString用法
 * @date:18/10/31
 */
@Setter
@ToString(exclude = "id")
public class PlugLombokDemo2 {
    private static final int STATIC_VAR = 10;
    private String name;
    private Shape shape = new Square(5, 10);
    private String[] tags;
    private int id;


    @ToString
    public static class Shape {
        private int color;

    }

    @ToString(callSuper = true, includeFieldNames = true)
    public static class Square extends Shape {
        private final int width, height;

        public Square(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        PlugLombokDemo2 plugLombokDemo2 = new PlugLombokDemo2();

        plugLombokDemo2.setId(1);
        plugLombokDemo2.setName("abc");
        plugLombokDemo2.setTags(new String[]{"a", "b", "c"});

        Shape shape = new Square(1, 2);
        plugLombokDemo2.setShape(shape);

        System.out.println(plugLombokDemo2.toString());
    }

}
