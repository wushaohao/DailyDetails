package designModels.Observer.imooclesson;

/**
 * @author:wuhao
 * @description:具体的目标对象,负责把有关状态存入到相应的观察者对象中
 * @date:18/8/12
 */
public class ConcreteSubject extends Subject{

    private String subjectState;

    public String getSubjectState() {
        return subjectState;
    }

    public void setSubjectState(String subjectState) {
        this.subjectState = subjectState;
        this.notifyObservers();
    }


}