package app.originality.com.originality.modules.photo.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.ImageLoader;

import app.originality.com.originality.R;
import app.originality.com.originality.widget.zoomimage.ImageViewTouchBase;
import app.originality.com.originality.widget.zoomimage.SmoothImageView;

public class ScrollDialog extends Dialog {

    private Activity activity;
    private View parentView;
    //    private SimpleDraweeView mSimpleDraweeView;
    private SmoothImageView mSmoothImageView;
    private String url;

    public ScrollDialog(Context context) {
//        super(context);
        this(context, R.style.dialog);
    }
//
//    public ScrollDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
//        super(context, cancelable, cancelListener);
//        init();
//    }

    public ScrollDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.activity = (Activity) context;
        init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(
                (WindowManager.LayoutParams) params);
    }

    private void init() {
        parentView = LayoutInflater.from(activity).inflate(R.layout.item_scroll_dialog, null);
//        mSimpleDraweeView = (SimpleDraweeView) parentView.findViewById(R.id.id_dialog_img);
        mSmoothImageView = (SmoothImageView) parentView.findViewById(R.id.id_dialog_img);
        this.setContentView(parentView);
    }

    public void setImageUrl(String url) {
        this.url = url;
    }


    public void setSommthImage(int locationX, int locationY, int width, int height) {
        mSmoothImageView.setOriginalInfo(width, height, locationX, locationY);
        mSmoothImageView.transformIn();
//        mSmoothImageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        mSmoothImageView.getLayoutParams().width = -1;
        mSmoothImageView.getLayoutParams().height = -1;
        mSmoothImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ImageLoader.getInstance().displayImage(url, mSmoothImageView);
    }

}