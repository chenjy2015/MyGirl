package app.originality.com.originality.util;

import android.content.Context;
import android.content.Intent;

import app.originality.com.originality.modules.photo.ui.PhotoListActivity;
import app.originality.com.originality.ui.GuidePageActivity;
import app.originality.com.originality.ui.HomeActivity;
import app.originality.com.originality.ui.LoginActivity;
import app.originality.com.originality.ui.RegisterActivity;

public class JumpManager {

    /**
     * 进入引导页面
     *
     * @param context
     */
    public static void jumpGuidePageActivity(Context context) {
        Intent intent = new Intent(context, GuidePageActivity.class);
        context.startActivity(intent);
    }

    /**
     * 进入登录界面
     *
     * @param context
     */
    public static void jumpLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    /**
     * 进入注册界面
     *
     * @param context
     */
    public static void jumpRegisterActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    /**
     * 进入首页
     *
     * @param context
     */
    public static void jumpHomeActivity(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    /**
     * 进入相册界面
     *
     * @param context
     */
    public static void jumpPhotoAlbumActivity(Context context) {
        Intent intent = new Intent(context, PhotoListActivity.class);
        context.startActivity(intent);
    }

}