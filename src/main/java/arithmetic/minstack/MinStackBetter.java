package arithmetic.minstack;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * @author:wuhao
 * @description:获取栈最小值(算法调优)
 * @date:18/10/9
 */
public class MinStackBetter {
    private List<Integer> data = new ArrayList<>();
    private List<Integer> mins = new ArrayList<>();

    /**
     * 入栈操作
     *
     * @param num
     */
    public void push(int num) {
        data.add(num);

        if (mins.size() == 0) {
            // mins集合中存放的是最小值的索引
            mins.add(0);
        } else {
            int min = getMin();
            if (num < min) {
                mins.add(data.size() - 1);
            }
        }
    }

    /**
     * 出栈操作
     *
     * @return
     */
    public int pop() {
        if (data.size() == 0) {
            throw new EmptyStackException();
        }
        //pop时先获取索引
        int popIndex = data.size() - 1;
        //获取mins栈顶元素 他是最小值索引
        int minIndex = mins.get(mins.size() - 1);
        // 如果pop出去的索引就是最小值索引，mins才出栈
        if (popIndex == minIndex) {
            mins.remove(mins.size() - 1);
        }
        return data.remove(data.size() - 1);
    }

    /**
     * 获取最小值
     *
     * @return
     */
    public int getMin() {
        if (data.size() == 0) {
            // 直接抛出异常给使用者捕获 不捕获异常那么连编译都不会通过
            throw new EmptyStackException();
        }
        // 获取mins栈顶元素，它是最小值索引
        int minIndex = mins.get(mins.size() - 1);
        return data.get(minIndex);
    }


}
