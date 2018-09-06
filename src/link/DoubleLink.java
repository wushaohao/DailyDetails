package link;

/**
 * @author:wuhao
 * @description:双向链表
 * @date:18/9/5
 */
public class DoubleLink<T> {

    /**
     * 节点
     *
     * @param <T>
     */
    private class Node<T> {
        /**
         * 节点值
         */
        private T value;

        /**
         * 前一个节点
         */
        private Node<T> pre;

        /**
         * 下一个节点
         */
        private Node<T> next;

        public Node(T value, Node<T> pre, Node<T> next) {
            this.value = value;
            this.pre = pre;
            this.next = next;
        }
    }

    /**
     * 链表长度
     */
    private int size;

    /**
     * 链表头
     */
    private Node<T> head;

    public DoubleLink() {
        /**
         * 头结点不存储值 并且头结点初始化时 就一个头结点。
         * 所以头结点的前后节点都是自己
         * 并且这个链表的长度为0；
         */
        head = new Node<>(null, null, null);
        head.pre = head.next;
        head = head.next;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    /**
     * 判断链表长度是否为0
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断索引是否超出范围
     *
     * @param index
     */
    public void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return;
    }

    /**
     * 通过索引获取链表当前节点
     *
     * @param index
     * @return
     */
    public Node<T> getNode(int index) {
        checkIndex(index);
        /**
         * 当索引的值小于该链表长度的一半时，那么从链表的头结点开始向后找是最快的
         */
        if (index < size / 2) {
            Node<T> cur = head.next;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            return cur;
        }

        /**
         * 当索引值位于链表的后半段时，则从链表的另端开始找是最快的
         */
        Node<T> cur = head.pre;
        int newIndex = size - (index + 1);
        for (int i = 0; i < newIndex; i++) {
            cur = cur.pre;
        }
        return cur;
    }

    /**
     * 获取当前节点值
     * @param cur
     * @return
     */
    public T getValue(Node<T> cur) {
        return cur.value;
    }

    /**
     * 获取第一个节点的值
     * @return
     */
    public T getFirst() {
        return getValue(getNode(0));
    }

    /**
     * 获取最后一个节点值
     * @return
     */
    public T getLast() {
        return getValue(getNode(size - 1));
    }

    /**
     * 插入节点
     * @param index
     * @param value
     */
    public void insert(int index, T value) {
        //如果插入时 链表是空的
        if (index == 0) {
            Node<T> cur = new Node<T>(value, head, head.next);
            head.next.pre = cur;
            head.next = cur;
            size++;
            return;
        }
        /**
         * 先根据给出的插入位置 找到该链表原来在此位置的节点
         */
        Node<T> node = getNode(index);
        /**
         *放置的位置的前一个节点就是原节点的前置节点 而后节点就是原节点
         */
        Node<T> cur = new Node<>(value, node.pre, node);
        /**
         * 现将该位置也就是 原节点的前节点的后节点 赋值成为新节点
         * 然后将新节点的后置节点的值赋值成为原节点
         */
        node.pre.next = cur;
        node.pre = cur;
        size++;
    }

    /**
     * 向表头插入数据
     * @param value
     */
    public void insertTo(T value)
    {
        insert(0,value);
    }

    /**
     * 将元素插入到链表的尾部
     * @param value
     */
    public void insertToTail(T value){
        Node<T> cur = new Node<>(value, head.pre, head);
        /**
         * head.pre 代表原来的尾部节点
         * 遵循两个原则 一 新插入节点的前一个节点的后一个节点为新节点。新节点的后一个节点的前一个节点是新节点
         */
        head.pre.next = cur;
        head.pre = cur;
        size++;
    }

    /**
     * 删除节点
     * @param index
     */
    public void del(int index) {
        checkIndex(index);
        Node<T> cur = getNode(index);

        /**
         * 记住此时的指针还没断开 赋值以后才相当于断开
         */
        cur.pre.next = cur.next;
        cur.next.pre = cur.pre;

        size--;
        cur = null;
        return;
    }

    /**
     * 删除第一个节点
     */
    public void delFirst() {
        del(0);
    }

    /**
     * 删除最后一个节点
     */
    public void delLast() {
        del(size-1);
    }
}
