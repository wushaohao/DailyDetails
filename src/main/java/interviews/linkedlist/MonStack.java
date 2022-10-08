package interviews.linkedlist;

import java.util.Stack;

/**
 * @author:wuhao
 * @description:单调栈
 * @date:2022/8/12
 */
public class MonStack {
    /**
     * 栈:后进先出
     * 单调递增栈:单调递增栈就是从栈底到栈顶数据是从大到小
     * 单调递减栈:单调递增栈就是从栈底到栈顶数据是从小到大
     * 常用方法：
     * push:压栈 栈顶添加元素
     * pop:弹出栈顶元素
     * peek:查看栈顶元素 不弹出
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] num = new int[]{3, 1, 2, 4};
        System.out.println("min数组区间最小值的和是:" + computer(num));
    }

    /**
     * 【遍历数组中的每个位置 从当前位置出发 向左和向右寻找第一个比当前元素小的元素 找到左右边界后边界内的元素的最小元素都是当前遍历到的元素】
     * 其实思想就是:
     * 1.找出对于每一个数字 他能在多少子数组中充当的最小值的身份
     * 2.维护一个单调递增的栈 在出栈的时候对栈顶进行处理 我们保证对于栈顶元素做处理时:它大于它在栈内的下一个元素 也大于当前遍历到的元素
     *
     * @param num
     * @return
     */
    private static int computer(int[] num) {
        Stack<Integer> s = new Stack<>();
        int n = num.length;
        int res = 0;
        int mod = (int) (1e9 + 7);

        int k, j;

        for (int i = 0; i <= n; i++) {
            while (!s.empty() && num[s.peek()] > (i == n ? 0 : num[i])) {
                j = s.pop();
                k = s.isEmpty() ? -1 : s.peek();
                res = (res + num[j] * (i - j) * (j - k)) % mod;
            }
            s.push(i);
        }
        return res;
    }
}
