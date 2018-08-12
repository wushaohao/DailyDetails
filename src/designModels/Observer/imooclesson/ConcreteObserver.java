package designModels.Observer.imooclesson;

/**
 * @author:wuhao
 * @description:具体的观察者对象,实现更新的方法,使自身的状态和目标的状态保持一致
 * @date:18/8/12
 */
public class ConcreteObserver implements Observer{

    /**
     * 观察者状态
     */
    private String observerState;

    /**
     * 更新的接口
     *
     * @param subject 传入目标对象,方便获取响应的目标对象的状态
     */
    @Override
    public void update(Subject subject) {
        // 获取目标类的状态同步到观察者的状态中
        observerState = ((ConcreteSubject)subject).getSubjectState();
    }
}
