package interviews.software;


import java.util.HashMap;
import java.util.Map;

/**
 * @author:wuhao
 * @description:LRU缓存-双向链表删除最近不常使用的节点
 * @date:2022/8/24
 */
public class LRUCache {
    Map<Integer, ListNode> map;

    int capacity;

    ListNode head;
    ListNode tail;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);

        // 双向链表
        this.capacity = capacity;
        head.next = tail;
        tail.pre = head;
    }

    /**
     * 获取链表数据
     *
     * @param key
     * @return
     */
    public int get(int key) {
        ListNode node = map.get(key);
        if (node == null) {
            return -1;
        }
        moveToTail(node, node.value);
        return node.value;
    }

    /**
     * 存放链表数据
     *
     * @param key
     * @param value
     */

    public void put(int key, int value) {
        ListNode node = map.get(key);
        if (node != null) {
            moveToTail(node, value);
        } else {
            if (map.size() == capacity) {
                ListNode delNode = head.next;
                delete(delNode);
                map.remove(delNode.key);
            }
            ListNode newNode = new ListNode(key, value);
            insert(newNode);
            map.put(key, newNode);
        }
    }


    /**
     * 移动经常访问/新数据到链表尾部
     *
     * @param node
     * @param value
     */
    private void moveToTail(ListNode node, int value) {
        delete(node);
        node.value = value;
        insert(node);
    }

    /**
     * 删除节点node
     *
     * @param node
     */
    private void delete(ListNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    /**
     * 插入到链表尾部
     *
     * @param node
     */
    private void insert(ListNode node) {
        node.pre = tail.pre;
        node.pre.next = node;
        node.next = tail;
        tail.pre = node;

    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(16);
        System.out.println(cache.get(1));
    }

    class ListNode {
        public int key;
        public int value;
        public ListNode next;
        public ListNode pre;

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

}

