package definexception;


/**
 * @author:wuhao
 * @description:异常最先匹配原则
 * @date:2019/12/17
 */
public class TestFirstException {
    /**
     * 抛出异常的时候,异常处理系统会按照代码的书写顺序找出"最近"的处理程序。找到匹配的处理程序之后,它就认为异常将得到处理,然后就不再继续查找。
     * 查找的时候并不要求抛出的异常同处理程序的异常完全匹配。派生类的对象也可以配备其基类的处理程序
     *
     * @param args
     */
    public static void main(String[] args) {
        //
        try {
            throw new Sneeze();
        } catch (Sneeze sneeze) {
            System.out.println("Caught Sneeze!");
        } catch (Annoyance annoyance) {
            System.out.println("Caught Annoyance!");
        }

        try {
            throw new Sneeze();
        } catch (Annoyance annoyance) {
            System.out.println("Caught Annoyance--!");
        }

        /**
         * catch(Annoyance a)会捕获Annoyance以及所有从它派生的异常。捕获基类的异常,就可以匹配所有派生类的异常
         */
//    try {
//      throw new Sneeze();
//    } catch (Annoyance annoyance) {
//        System.out.println("Caught Annoyance~~~!");
//    } catch (Sneeze sneeze) {
//        System.out.println("Caught Sneeze!");
//    }
    }
}

class Annoyance extends Exception {
}

class Sneeze extends Annoyance {
}

