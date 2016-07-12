package app.originality.com.originality.util;

import android.content.Context;
import android.content.Intent;

import com.bm.library.Info;

import app.originality.com.originality.bean.PhotoGroupVO;
import app.originality.com.originality.bean.SpaceImageVO;
import app.originality.com.originality.modules.photo.ui.SpaceImageDetailActivity1;
import app.originality.com.originality.modules.photo.ui.SpaceImageDetailActivity2;
import app.originality.com.originality.modules.photo.ui.VerticalDisplayPhotoListActivity;
import app.originality.com.originality.modules.photo.ui.SpaceImageDetailActivity;
import app.originality.com.originality.modules.photo.ui.HorizontalDisplayPhotoListActivity;
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
    public static void jumpPhotoAlbumActivity(Context context, PhotoGroupVO photoGroupVO) {
        jumpVerticalDisplayPhotoListActivity(context, photoGroupVO);
    }


    /**
     * 进入大图片浏览界面
     *
     * @param context
     * @param spaceImageVO
     */
    public static void jumpSpaceImageActivity(Context context, SpaceImageVO spaceImageVO) {
        Intent intent = new Intent(context, SpaceImageDetailActivity.class);
        intent.putExtra("SpaceImageVO", spaceImageVO);
        context.startActivity(intent);
    }

    /**
     * 进入大图片浏览界面
     *
     * @param context
     */
    public static void jumpSpaceImageActivity2(Context context) {
        Intent intent = new Intent(context, SpaceImageDetailActivity2.class);
        context.startActivity(intent);
    }


    /**
     * 进入横向浏览图片模式 浏览图片列表
     *
     * @param context
     * @param photoGroupVO
     */
    public static void jumpHorizontalDisplayPhotoListActivity(Context context, PhotoGroupVO photoGroupVO, SpaceImageVO mSpaceImageVO) {
        Intent intent = new Intent(context, HorizontalDisplayPhotoListActivity.class);
        intent.putExtra("PhotoGroupVO", photoGroupVO);
        intent.putExtra("SpaceImageVO", mSpaceImageVO);
        context.startActivity(intent);
    }

    /**
     * 进入纵向浏览图片模式 浏览图片列表
     *
     * @param context
     * @param photoGroupVO
     */
    public static void jumpVerticalDisplayPhotoListActivity(Context context, PhotoGroupVO photoGroupVO) {
        Intent intent = new Intent(context, VerticalDisplayPhotoListActivity.class);
        intent.putExtra("PhotoGroupVO", photoGroupVO);
        context.startActivity(intent);
    }

}