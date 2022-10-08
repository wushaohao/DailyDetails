package interviews.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wuhao
 * @description:单链表是否有环
 * @date:2022/8/8
 */
public class LinkedCycle {
    public static void main(String[] args) {
        ListNode node1 = new ListNode("A", null);
        ListNode node2 = new ListNode("B", null);
        ListNode node3 = new ListNode("C", null);
        ListNode node4 = new ListNode("D", null);
        ListNode node5 = new ListNode("E", null);
        ListNode node6 = new ListNode("F", null);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node3;

        System.out.println("相遇节点是:" + isCycle(node1).item);
        System.out.println("入口节点是:" + enterPoint(node1).item);

    }

    static class ListNode<T> {
        T item;
        ListNode next;

        public ListNode(T item, ListNode next) {
            this.item = item;
            this.next = next;
        }
    }

    public static ListNode isCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        // 创建初始指针
        ListNode slow = head;
        // 创建后移动指针
        ListNode fast = head;

        // 后移指针向后移动  直到有相同的节点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return slow;
            }
        }
        return null;
    }

    public static ListNode enterPoint(ListNode node) {
        ListNode meetNode = isCycle(node);
        // 链表第一个指针
        ListNode first = node;
        while (first != meetNode) {
            first = first.next;
            meetNode = meetNode.next;
        }
        return meetNode;
    }
}

