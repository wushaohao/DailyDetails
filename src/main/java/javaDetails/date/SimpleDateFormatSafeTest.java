package javaDetails.date;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author:wuhao
 * @description:SimpleDateFormat安全性测试
 * @date:2019/4/8
 */
public class SimpleDateFormatSafeTest {
    /**
     * 方法三:使用线程安全的ThreadLocal
     */
//    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();

    /**
     * 定义一个全局变量的SimpleDateFormat
     */
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 使用ThreadFactoryBuilder定义一个线程池
     */
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
    private static ExecutorService pool = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    /**
     * 定义CountDownLatch,保证所有的子线程执行完成后主线程再执行
     */
    private static CountDownLatch latch = new CountDownLatch(100);

    public static void main(String[] args) {
        Set<String> dates = Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i < 100; i++) {
            // 方法二:使用同步锁
//            synchronized (simpleDateFormat) {
//
//            }
            //获取当前时间
            Calendar calendar = Calendar.getInstance();
            int finalI = i;
            pool.execute(() -> {
                //方法一:局部变量 但是没执行一个线程就new一个对象 内存可能消耗更大
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //时间增加
                calendar.add(Calendar.DATE, finalI);
                //通过SDF把时间转换成字符串
                String dataString = simpleDateFormat.format(calendar.getTime());
                //把字符串放入set
                dates.add(dataString);
                latch.countDown();
            });
        }
        try {
            //主线程阻塞
            latch.await();
            //输出去重后时间个数
            System.out.println(dates.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * SimpleDateFormat中的format(Date),parse(str)方法中使用的是calendar.set(data)来设置时间的
     * 而calendar是直接定义的成员变量,这就是问题所在的关键
     * 所以解决办法:
     * 1.将SimpleDateFormat定义为局部变量
     * 2.加锁
     * 3.ThreadLocal
     * 4.使用DateTimeFormatter
     */

}
