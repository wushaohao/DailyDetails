package arithmetic;

import java.util.Stack;

/**
 * @author:wuhao
 * @description:栈实现队列
 * @date:2019/4/2
 */
public class StackRealizeQueue {
    private Stack<Integer> stackA = new Stack<>();
    private Stack<Integer> stackB = new Stack<>();

    /**
     * 入队
     * @param element
     */
    public void enQueue(int element) {
        stackA.push(element);
    }

    /**
     * 出队
     * @return
     */
    public Integer deQueue() {
        if (stackB.isEmpty()) {
            if (stackA.isEmpty()) {
                return null;
            }
            transfer();
        }
        return stackB.pop();
    }

    /**
     * 栈A移至栈B
     */
    public void transfer() {
        while (!stackA.isEmpty()) {
            stackB.push(stackA.pop());
        }
    }

    public static void main(String[] args) {
        /**
         *  栈:先进后出 队列:先进先出
         *  两个空栈A、B
         *  入队操作:先将信息压入A栈,再将A栈元素出栈,直接压入B栈
         *  出队操作:B栈直接出栈
         *  原理:
         *      元素1、2、3、4
         *  栈操作是:入栈就是 1、2、3、4 出栈就是4、3、2、1
         *  队列操作是:入队列 1、2、3、5 出队列就是1、2、3、4
         *  用栈实现队列就是第一个入栈(1、2、3、4)的操作就相当于入队列,出队列就是利用另一个B栈来暂存A栈弹出来的元素(4、3、2、1),那么B栈再弹出来
         *  的元素就是1、2、3、4,也就是队列的出队顺序
         *  时间复杂度:
         *      入队列的复杂度是O(1),至于出队列的操作,如果涉及到栈A和栈B的元素迁移,时间复杂度是O(n),如果不用迁移那么就是O(1)
         */
        StackRealizeQueue stackRealizeQueue = new StackRealizeQueue();
        stackRealizeQueue.enQueue(1);
        stackRealizeQueue.enQueue(2);
        stackRealizeQueue.enQueue(3);
        System.out.println(stackRealizeQueue.deQueue());
        System.out.println(stackRealizeQueue.deQueue());
        stackRealizeQueue.enQueue(4);
        System.out.println(stackRealizeQueue.deQueue());
        System.out.println(stackRealizeQueue.deQueue());
    }
}
