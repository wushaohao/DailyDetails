package designModels.Observer;

/**
 * Created by wuhao on 17/5/27.
 */
public class AndroidPhone extends AbstractPhone{
    public AndroidPhone(String name){
        this.name=name;
    }

    @Override
    public void display() {
        System.out.println("Android user "+this.getName()+" receive message "+ wppData.getMessage());
    }
}
