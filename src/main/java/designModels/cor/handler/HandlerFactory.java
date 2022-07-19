package designModels.cor.handler;

/**
 * @author:wuhao
 * @description:handler类处理工厂累类
 * @date:18/7/29
 */
public class HandlerFactory {

    public static PreHandler createHandler() {
        Sales sales = new Sales();
        Manager manager = new Manager();
        Director director = new Director();
        CEO ceo = new CEO();

        sales.setPreHandler(manager);
        manager.setPreHandler(director);
        director.setPreHandler(ceo);
        return sales;
    }
}
