package designModels.responsibilitychain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author:wuhao
 * @description:请求类
 * @date:18/7/25
 */
public class Client {
    public static void main(String[] args) {
        Random random = new Random();
        List<Woman> lists = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            lists.add(new Woman(random.nextInt(4), "看电影去!"));
        }

        Handler father = new Father();
        Handler husband = new Husband();
        Handler son = new Son();

        father.setNext(husband);
        husband.setNext(son);

        for (Woman w : lists) {
            father.HandMessage(w);
        }
    }
}
