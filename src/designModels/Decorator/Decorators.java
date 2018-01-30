package designModels.Decorator;

/**
 * Created by wuhao on 17/5/24.
 */
public class Decorators implements Cafe{

    private Cafe cafe;

    public Decorators(Cafe cafe){
        this.cafe=cafe;
    }

    @Override
    public void getCafe() {
        cafe.getCafe();
    }


}