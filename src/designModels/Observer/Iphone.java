package designModels.Observer;

/**
 * Created by wuhao on 17/5/27.
 */
public class Iphone extends AbstractPhone {
    public Iphone(String name){
        this.name=name;
    }

    @Override
    public void display() {
        System.out.println("Apple user "+this.getName()+" receive message "+ wppData.getMessage());
    }
}
