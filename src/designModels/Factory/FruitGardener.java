package designModels.Factory;

/**
 * Created by wuhao on 17/5/1.
 */
public class FruitGardener {

    public static void factory(String fruitName) throws Exception {

        if (fruitName.equalsIgnoreCase("apple")){
            new Apple().plant();
        }else if (fruitName.equalsIgnoreCase("grape")){
            new Grape().plant();
        }else{
            throw new Exception("不存在此种类的水果!");
        }
    }

    public static void main(String[] args) throws Exception{
        factory("Apple");
    }
}
