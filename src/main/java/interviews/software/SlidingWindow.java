package interviews.software;

/**
 * @author:wuhao
 * @description:滑动窗口算法
 * @date:2022/8/7
 */
public class SlidingWindow {
    public static void main(String[] args) {
        int[] num = new int[]{3, 6, 9, 5, 2, 7};
        int target = 13;
        System.out.println("遍历结果的长度是:" + computer(target, num));

    }

    public static int computer(int target, int[] num) {
        int res = num.length + 1;
        // 滑动窗口的前后指针
        int slowIndex = 0;
        int fastIndex = 0;
        int sum = 0;

        // 后指针遍历
        while (fastIndex < num.length) {
            // 后指针向后移动
            sum += num[fastIndex];

            while (sum >= target) {
                res = Math.min(res, (fastIndex - slowIndex + 1));
                // 前指针向后移动
                sum -= num[slowIndex];
                slowIndex++;
            }
            // 不满足结果 后指针向后继续移动
            fastIndex++;
        }

        return res == num.length + 1 ? 0 : res;
    }
}
