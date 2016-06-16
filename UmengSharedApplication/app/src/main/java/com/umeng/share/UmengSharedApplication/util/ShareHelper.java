package com.umeng.share.UmengSharedApplication.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.umeng.share.UmengSharedApplication.R;
import com.umeng.share.UmengSharedApplication.bean.PlatformVO;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMusic;

import java.util.List;
import java.util.ArrayList;

public class ShareHelper {

    public static String SHARE_TITLE = "分享测试标题";
    public static String SHARE_CONTENT = "分享测试内容";
    public static UMImage mUMImage;     //分享图片
    public static UMusic mMusic;        //分享语音
    public static UMVideo video;        //分享多媒体视频



    /**
     * 初始化分享配置 放在程序入口 或者APPLIACTION中
     */
    public static void init() {
        //各个平台的配置，建议放在全局Application或者程序入口
        //微信    wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        //豆瓣RENREN平台目前只能在服务器端配置
        //新浪微博
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        //易信
        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
        PlatformConfig.setAlipay("2015111700822536");
        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
        PlatformConfig.setPinterest("1439206");
    }

    public static List<PlatformVO> getShareResource(Context context) {
        String[] descriptions = context.getResources().getStringArray(R.array.share_platform);
        int[] icons = new int[]{R.drawable.btn_share_qq, R.drawable.btn_share_qq_zone, R.drawable.btn_share_weixin, R.drawable.btn_share_pengyouquan, R.drawable.btn_share_sina};
        List<PlatformVO> mData = new ArrayList<PlatformVO>();
        for (int i = 0; i < descriptions.length; i++) {
            PlatformVO platformBean = new PlatformVO();
            platformBean.setIcon(icons[i]);
            platformBean.setDescription(descriptions[i]);
            mData.add(platformBean);
        }
        return mData;
    }

    /**
     * @param listView ListView 或者是GridView
     * @param col      当前为GridView时 标识每行列数
     */
    public static void setListViewHeightBasedOnChildren(GridView listView, int col) {
        // 获取listview的adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
//        int col = listView.getColumnCout();
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            // 获取item的高度总和
            totalHeight += listItem.getMeasuredHeight() + (listItem.getMeasuredHeight() / 3);
        }

        // 获取listview的布局参数
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // 设置高度
        params.height = totalHeight;
        // 设置margin
        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        listView.setLayoutParams(params);
    }

    public static ShareAction getShareAction(Activity activity, String action, UMShareListener umShareListener) {
        ShareAction shareAction = new ShareAction(activity);
        String[] descriptions = activity.getResources().getStringArray(R.array.share_platform);
        shareAction.withTitle(SHARE_TITLE);
        shareAction.withText(SHARE_CONTENT);
        mUMImage = new UMImage(activity,"http://img4.duitang.com/uploads/blog/201311/04/20131104193715_NCexN.thumb.jpeg");
        shareAction.withMedia(mUMImage);
        //QQ
        if (action.equals(descriptions[0])) {
            shareAction.setPlatform(SHARE_MEDIA.QQ);
            //QQZone
        } else if (action.equals(descriptions[1])) {
            shareAction.setPlatform(SHARE_MEDIA.QZONE);
            //Wechat
        } else if (action.equals(descriptions[2])) {
            shareAction.setPlatform(SHARE_MEDIA.WEIXIN);
            //WechatMoments
        } else if (action.equals(descriptions[3])) {
            shareAction.setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE);
            //Sinaweibo
        } else if (action.equals(descriptions[4])) {
            shareAction.setPlatform(SHARE_MEDIA.SINA);
        }
        shareAction.setCallback(umShareListener);
        return shareAction;
    }
}