package collection;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * @author:wuhao
 * @description:栈和队列
 * @date:2022/10/8
 */
public class StackAndQueue {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        Queue<String> queue = new LinkedList<>();

        stack.push("Apple");
        stack.push("Banana");
        stack.push("Cherry");

        queue.add(stack.pop());
        stack.push("Dingleberry");
        stack.push("Eggplant");

        queue.add("Fig");

        stack.push(queue.remove());
        queue.add(stack.pop());
        queue.add(stack.pop());

        System.out.println(stack);
        System.out.println(queue);

        System.out.println(Math.round(22.9));
    }
}
