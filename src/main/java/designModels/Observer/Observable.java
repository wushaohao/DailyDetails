package designModels.Observer;


/**
 * Created by wuhao on 17/5/27.
 */
public interface Observable {

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void updateTerminals();

    void notifyObserver();

}
