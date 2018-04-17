package Serialize.fst;

/**
 * @author:wuhao
 * @description:序列化方式Main函数测试
 * @date:18/4/17
 */
public class SeralizeTest {

    public static int MAX = 10000;

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("caicai");
        user.setAge(24);
        user.setPassword("520520");

        System.out.println("序列化 反序列化,对比测试....");

        long size = 0;
        long time1 = System.currentTimeMillis();

        /**
         * jdk 序列化测试
         */
        for (int i = 0; i < MAX; i++) {
            byte[] bytes = JRedisSerializationUtils.jdkSerialize(user);
            size += bytes.length;
            JRedisSerializationUtils.jdkDeSerialize(bytes);
        }
        System.out.println("原生序列化方案[序列化10000次]耗时:" + (System.currentTimeMillis() - time1) + "ms size:=" + size);

        /**
         * Kryo 序列化
         */
        size = 0;
        long time2 = System.currentTimeMillis();

        for (int i = 0; i < MAX; i++) {
            byte[] bytes = JRedisSerializationUtils.kryoSerizlize(user);
            size += bytes.length;
            User kryoUser = (User) JRedisSerializationUtils.kryoDeSerialize(bytes);
        }
        System.out.println("kryo序列化方案[序列化10000次]耗时："
                + (System.currentTimeMillis() - time2) + "ms size:=" + size);


        /**
         *  FST 序列化
         */
        size = 0;
        long time3 = System.currentTimeMillis();
        for (int i = 0; i < MAX; i++) {
            byte[] bytes = JRedisSerializationUtils.fstSerialize(user);
            size += bytes.length;
            User fstUser = (User) JRedisSerializationUtils.fstDeserialize(bytes);
        }
        System.out.println("fst序列化方案[序列化10000次]耗时："
                + (System.currentTimeMillis() - time3) + "ms size:=" + size);
    }
}
