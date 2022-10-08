package interviews.linkedlist;

/**
 * @author:wuhao
 * @description:两个链表相交,求入口节点
 * @date:2022/8/12
 */
public class TwoLinked {
    public static void main(String[] args) {

    }

    class ListNode<T> {
        T item;
        LinkedCycle.ListNode next;

        public ListNode(T item, LinkedCycle.ListNode next) {
            this.item = item;
            this.next = next;
        }
    }
}
