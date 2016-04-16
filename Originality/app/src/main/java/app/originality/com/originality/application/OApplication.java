package app.originality.com.originality.application;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

import app.originality.com.originality.bean.UserInfor;
import app.originality.com.originality.storage.OSPUtils;


public class OApplication extends Application {

    public static UserInfor mUserInfor;
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        init();
        //facebook 图片处理框架初始化
        Fresco.initialize(this);
    }

    public static void init() {
        mUserInfor = OSPUtils.getUserInfor(mContext);
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

}