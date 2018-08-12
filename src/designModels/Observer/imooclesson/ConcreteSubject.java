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
        // 目标类也就是被观察者 状态发生改变 发布状态 通知观察者
        this.subjectState = subjectState;
        this.notifyObservers();
    }


}
