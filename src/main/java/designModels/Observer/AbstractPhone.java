package designModels.Observer;

/**
 * Created by wuhao on 17/5/27.
 */
public class AbstractPhone implements Observer, Displayable {

    protected WPPData wppData;
    protected String name;


    @Override
    public void dataUpdate(Observable observable) {
        this.wppData = (WPPData) observable;
        display();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void display() {

    }
}
