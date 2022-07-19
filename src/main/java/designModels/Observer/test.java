package designModels.Observer;

/**
 * Created by wuhao on 17/5/27.
 */
public class test {

    public static void main(String[] args) {
        WPPData message = new WPPData();
        message.setMessage("China beated Korean");
        AndroidPhone androidPhone = new AndroidPhone("Curry");
        AndroidPhone androidPhone1 = new AndroidPhone("Durant");

        Iphone iphone = new Iphone("James");
        message.addObserver(androidPhone);
        message.addObserver(androidPhone1);
        message.addObserver(iphone);

        message.notifyObserver();

        System.out.println("*******************");

        message.setMessage("China basketball beates America");
        message.removeObserver(androidPhone);
        message.removeObserver(androidPhone1);
        message.notifyObserver();

    }
}
