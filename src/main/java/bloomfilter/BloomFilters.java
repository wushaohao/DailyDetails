package bloomfilter;

import org.junit.Assert;

/**
 * @author:wuhao
 * @description:布隆过滤器
 * @date:18/12/5
 */
public class BloomFilters {
    /**
     * 数组大小
     */
    private int arraySize;

    /**
     * 数组
     */
    private int[] array;

    private static int SIZE = 10000000;


    public BloomFilters(int arraySize) {
        this.arraySize = arraySize;
        array = new int[arraySize];
    }

    /**
     * 写入数据
     *
     * @param key
     */
    public void add(String key) {
        int first = hashCode1(key);
        int second = hashCode2(key);
        int third = hashCode3(key);

        array[first % arraySize] = 1;
        array[second % arraySize] = 1;
        array[third % arraySize] = 1;
    }

    /**
     * 判断是否存在
     *
     * @param key
     * @return
     */
    public boolean check(String key) {
        int first = hashCode1(key);
        int second = hashCode2(key);
        int third = hashCode3(key);

        int firstIndex = array[first % arraySize];
        if (firstIndex == 0) {
            return false;
        }
        int secondIndex = array[second % arraySize];
        if (secondIndex == 0) {
            return false;
        }
        int thirdIndex = array[third % arraySize];
        if (thirdIndex == 0) {
            return false;
        }

        return true;
    }

    /**
     * hash算法1
     *
     * @param key
     * @return
     */
    private int hashCode1(String key) {
        int hash = 0;
        int i;
        for (int j = 0; j < key.length(); j++) {
            hash = 33 * hash + key.charAt(j);
        }
        return Math.abs(hash);
    }

    /**
     * hash算法2
     *
     * @param key
     * @return
     */
    private int hashCode2(String key) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash ^ key.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return Math.abs(hash);
    }

    /**
     * hash算法3
     *
     * @param key
     * @return
     */
    private int hashCode3(String key) {
        int hash, i;
        for (hash = 0, i = 0; i < key.length(); ++i) {
            hash += key.charAt(i);
            hash += hash << 10;
            hash ^= hash >> 6;
        }
        hash += hash << 13;
        hash ^= hash >> 11;
        hash += hash << 15;
        return Math.abs(hash);
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        BloomFilters bloomFilters = new BloomFilters(SIZE);
        for (int i = 0; i < SIZE; i++) {
            bloomFilters.add(i + "");
        }
        Assert.assertTrue(bloomFilters.check(1 + ""));
        Assert.assertTrue(bloomFilters.check(1000 + ""));
        Assert.assertTrue(bloomFilters.check(49990 + ""));
        Assert.assertTrue(bloomFilters.check(99990 + ""));
        Assert.assertTrue(bloomFilters.check(400230340 + ""));

        long end = System.currentTimeMillis();
        System.out.println("执行时间:" + (end - start));
    }

}
