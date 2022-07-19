package reference.softreference;

/**
 * @author:wuhao
 * @description:软引用测试
 * @date:18/8/7
 */
public class SoftReferenceTest {
    private ImageCacheManager cacheManager = ImageCacheManager.getImageCacheManager();

    /**
     * 设置启动参数
     *
     * @param args
     */
    public static void main(String[] args) {
        SoftReferenceTest tester = new SoftReferenceTest();
        //先把数据load进来
        for (int i = 0; i < 10; i++) {
            tester.cacheManager.get(i);
        }
        //一张特殊的图片，这里保存了一个对这张图片的强引用，所以该对象不会被回收
        Image myPhoto = tester.cacheManager.get(7);
        System.out.println("--------------------------");
        System.out.println(myPhoto);
        System.out.println("--------------------------");

        //打印图片
        for (int i = 0; i < 10; i++) {
            tester.printImage(i);
        }
    }

    private void printImage(int id) {
        System.out.println(cacheManager.get(id));
    }
}
