package google.guava;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

/**
 * @author:wuhao
 * @description:guava中的bloom过滤器
 * @date:18/11/16
 */
@Slf4j
public class BloomFilterDemo {

    /**
     * Guava 会通过你预计的数量以及误报率帮你计算出你应当会使用的数组大小 numBits 以及需要计算几次 Hash 函数 numHashFunctions
     * 可看下源码
     */
    private final BloomFilter<String> dealIdBloomFilter = BloomFilter.create(new Funnel<String>() {

        private static final long serialVersionUID = 1L;

        @Override
        public void funnel(String arg0, PrimitiveSink arg1) {

            arg1.putString(arg0, Charsets.UTF_8);
        }

    }, 1024 * 1024 * 32);

    public synchronized boolean containsAccount(String account) {
        if (StringUtils.isEmpty(account)) {
            log.warn("deal_id is null");
            return true;
        }

        boolean exists = dealIdBloomFilter.mightContain(account);
        if (!exists) {
            dealIdBloomFilter.put(account);
        }
        return exists;
    }

    public static void main(String[] args) {
        BloomFilterDemo bloomFilterDemo = new BloomFilterDemo();
        System.out.println("第一次是否存在:" + bloomFilterDemo.containsAccount("wuhaodeyx@163.com"));
        System.out.println("第二次是否存在:" + bloomFilterDemo.containsAccount("wuhaodeyx@163.com"));
        System.out.println("第三次是否存在:" + bloomFilterDemo.containsAccount("15605161307"));
        System.out.println("第四次是否存在:" + bloomFilterDemo.containsAccount("15605161307"));
    }
}
