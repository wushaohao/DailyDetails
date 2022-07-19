package reference.softreference;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:wuhao
 * @description:图片缓存管理
 * @date:18/8/7
 */
public class ImageCacheManager {
    private static ImageCacheManager imageCacheManager = null;

    private ImageCacheManager() {

    }

    /**
     * 作为一个管理器 单例模式
     *
     * @return
     */
    public static ImageCacheManager getImageCacheManager() {
        if (imageCacheManager == null) {
            synchronized (ImageCacheManager.class) {
                if (imageCacheManager == null) {
                    imageCacheManager = new ImageCacheManager();
                }
            }
        }
        return imageCacheManager;
    }


    // 保存数据
    private Map<Integer, SoftReference<Image>> cache = new HashMap<>();


    public void put(Image image) {
        cache.put(image.getId(), new SoftReference<Image>(image));
    }

    private Image loadImage(int id) {
        byte[][] data = new byte[1024][1024];
        data[1023][1023] = 1;
        return new Image(id, data);
    }

    public Image get(int id) {
        SoftReference<Image> ref = cache.get(id);
        // 没有放入缓存，第一次加载
        if (ref == null) {
            Image firstLoad = loadImage(id);
            //放入缓存
            put(firstLoad);
            return firstLoad;
        }

        //下面的情况是该图片已经被加载过，但是可能由于内存不足，又被回收了
        //为了便于理解，使用了if else分支方式，其实else不是必须的。
        //这两个分支，不管哪一个返回的都是对image的强引用
        Image cacheImage = ref.get();
        if (cacheImage == null) {
            //从真实介质中读取(此处模拟这个操作）
            Image imageGetFromMedia = loadImage(id);
            //缓存起来
            put(imageGetFromMedia);
            System.out.println("get image:" + id + "from media");
            return imageGetFromMedia;
        } else {
            System.out.println("get image:" + id + "from cache");
            return cacheImage;
        }
    }
}
