package app.originality.com.originality.application;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;

import app.originality.com.originality.R;
import app.originality.com.originality.bean.UserInfor;
import app.originality.com.originality.storage.OSPUtils;


public class OApplication extends Application {

    public static UserInfor mUserInfor;
    public static Context mContext;
    public static final String SDCARD_PATH = Environment.getExternalStorageDirectory().toString();
    public static final String IMAGES_FOLDER = SDCARD_PATH + File.separator + "demo" + File.separator + "images" + File.separator;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        init();
        //facebook 图片处理框架初始化
        Fresco.initialize(this);
        //ImageLoader 图片处理框架初始化
        initImageLoadConfig();
    }

    /**
     * 保存用户信息
     *
     * @param userInfor
     */
    public static void setUserInfor(UserInfor userInfor) {
        mUserInfor = userInfor;
        OSPUtils.setUserInfor(mContext, userInfor);
    }

    public static void init() {
        mUserInfor = OSPUtils.getUserInfor(mContext);
    }

    /**
     * 提取用户信息
     *
     * @return
     */
    public static UserInfor getUserInfor() {
        if (mUserInfor == null) {
            init();
        }
        return mUserInfor;
    }

    private void initImageLoadConfig() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_empty) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_error) // 设置图片加载或解码过程中发生错误显示的图片
//                .showImageOnLoading(R.mipmap.ic_launcher) //加载过程中默认图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                // .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .resetViewBeforeLoading(true)
                .build(); // 创建配置过得DisplayImageOption对象

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCache(new UnlimitedDiscCache(new File(IMAGES_FOLDER)))
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
//                        .writeDebugLogs() // 是否打印log日志
                .build();
        ImageLoader.getInstance().init(config);
    }
}