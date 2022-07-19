package jvm.error;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wuhao
 * @description:虚拟机栈、本地方法栈-内存溢出(内存溢出一般是出现在申请了较多的内存空间没有释放的情形),当扩展时无法申请到足够的内存时会抛出OutOfMemoryError异常
 * @date:18/9/1
 */
public class OutOfMemoryTest {
    public static void main(String[] args) {
        List lists = new ArrayList<>();
        for (; ; ) {
            int[] tmp = new int[1000000];
            lists.add(tmp);
        }
    }
}
