package arithmetic.minstack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wuhao
 * @description:获取最小值的栈
 * @date:18/10/9
 */
public class MinStack {
    private List<Integer> data = new ArrayList<>();
    private List<Integer> mins = new ArrayList<>();

    /**
     * 入栈(压栈)
     *
     * @param num
     */
    public void push(int num) {
        data.add(num);

        if (mins.size() == 0) {
            mins.add(num);
        } else {
            int min = getMin();
            if (num >= min) {
                mins.add(min);
            } else {
                mins.add(num);
            }
        }
    }

    /**
     * 出栈(弹栈)
     *
     * @return
     */
    public int pop() {
        return 0;
    }

    public int getMin() {
        // 栈为空
        if (mins.size() == 0) {
            // 这一步会有问题(如果入栈内容中包含-1,那么到底是栈为空还是push进来的值就无法解释了)
            return -1;
        }
        return mins.get(mins.size() - 1);
    }
}
