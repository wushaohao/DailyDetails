package link;

import java.util.*;

/**
 * @author:wuhao
 * @description:双向链表测试
 * @date:18/9/6
 */
public class DoubleLinkTest {
    private static LinkedList<String> dl;
    private static ArrayList<String> l;

    public void init() {
        dl = new LinkedList<String>();
        dl.add("N1");
        dl.add("N2");
        dl.add("N3");
        dl.add("N4");
        dl.add("N5");
        l = new ArrayList<String>();
        l.add("N1");
        l.add("N2");
        l.add("N3");
        l.add("N4");
        l.add("N5");
    }


    public void test() {
        for (String str : dl
                ) {
            System.out.println("双向链表:" + str);
        }
        for (String str : l
                ) {
            System.out.println("普通ArrayList:" + str);
        }
    }

    public void add() {
        // 双向链表独有 像头部添加元素
        dl.addFirst("N6");
        dl.addLast("N7");
    }

    public void del() {
        dl.remove("N1");

        for (String str : dl
                ) {
            System.out.println("双向链表:" + str);
        }

        l.remove("N1");
        for (String str : l
                ) {
            System.out.println("普通ArrayList:" + str);
        }
    }

    /**
     * 模仿栈的后进后出的pop
     * @return
     */
    public static String pop() {
        return dl.removeLast();
    }

    /**
     * 模仿栈
     */
    public void stack() {
        System.out.println(DoubleLinkTest.pop());
        for (String str : dl
                ) {
            System.out.println("双向链表:" + str);
        }
    }

    /**
     * Stack栈模拟
     */
    public void stackSimulate() {
        //堆栈是一种 “后进先出”  （LIFO） 的数据结构， 只能在一端进行插入（称为 “压栈” ） 或删除 （称为“出栈”）数据的操作
        Stack<String> stack = new Stack<>();
        //push():压入元素 进栈
        stack.push("N1");
        stack.push("N2");
        stack.push("N3");
        stack.push("N4");
        stack.push("N5");
        /**
         * pop():弹出元素 出栈
         * peek():查看栈顶对象而不移除它,返回:栈顶对象,抛出异常:EmptyStackException 如果堆栈式空的
         * boolean empty():测试堆栈是否为空,当且仅当堆栈中不含任何项时返回 true,否则返回false.
         * search(object o):返回对象在堆栈中位置,以 1 为基数,如果对象ｏ是栈中的一项,该方法返回距离栈顶最近的出现位置到栈顶的距离;栈中最上端项的距离
         */
        System.out.println("pop:" + stack.pop());
        for (String str : stack
                ) {
            System.out.println("Stack:"+str);
        }
    }

    /**
     * 实现队列
     * 队列的数据元素又称为队列元素 在队列中插入一个元素称为入队 从队列中删除一个元素称为出队列 因为队列只允许在一端插入在另一端删除
     * 所以最早进入队列的元素才能最先从队列中删除 故又称先进先出(FIFO)线性表
     * 队列队首删除 队尾插入
     */
    public void queue() {
        LinkedList<String> queue = new LinkedList<>();
        queue= (LinkedList<String>) Collections.synchronizedCollection(queue);
        DoubleLinkTest.addQueue(queue, "n1");
        DoubleLinkTest.addQueue(queue, "n2");
        DoubleLinkTest.addQueue(queue, "n3");

        System.out.println("-----------------------");
        DoubleLinkTest.delQueue(queue);
        DoubleLinkTest.foreach2(queue);
    }

    public static void foreach2(List<String> list) {
        for (String str : list
                ) {
            System.out.println(str);

        }
    }

    /**
     * 插入元素 (每次从队尾插入)
     * @param queue
     * @param o
     */
    public static void addQueue(LinkedList<String> queue, String o) {
        queue.add(o);
    }


    /**
     * 删除 (从队首进行删除)
     * @param queue
     */
    public static void delQueue(LinkedList<String> queue) {
        queue.removeFirst();
    }
}
