package interviews.linkedlist;


/**
 * @author:wuhao
 * @description:反转链表
 * @date:2022/8/25
 */
public class ReverseLink {
    public static void main(String[] args) {

    }

    public static ListNode reverseLink(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }

        // 初始化 head头节点
        ListNode pre = null;
        // cur链表的头节点
        ListNode cur = node;
        // 保存反转前的cur.next节点 防止反转后丢失
        ListNode temp = null;

        while (cur != null) {
            temp = cur.next;
            // 反转
            cur.next = pre;
            // pre后移
            pre = cur;
            //cur后移
            cur = temp;
        }

        return pre;
    }

    class ListNode<T> {
        T item;
        ListNode next;

        public ListNode(T item, ListNode next) {
            this.item = item;
            this.next = next;
        }
    }

}


