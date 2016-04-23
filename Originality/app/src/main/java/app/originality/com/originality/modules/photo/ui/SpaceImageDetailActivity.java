package app.originality.com.originality.modules.photo.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import app.originality.com.originality.R;
import app.originality.com.originality.factory.EventFactory;
import app.originality.com.originality.ui.BaseActivity;
import app.originality.com.originality.widget.zoomimage.ImageViewTouch;
import app.originality.com.originality.widget.zoomimage.SmoothImageView;
import de.greenrobot.event.EventBus;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.os.Handler;

import com.facebook.common.soloader.SoLoaderShim;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

/**
 * @author cjy
 * @version V1.0
 * @company MyGril
 * @Description 展示单张图片界面
 * @date 2016/4/22
 */
public class SpaceImageDetailActivity extends BaseActivity implements View.OnLongClickListener, View.OnClickListener {

//    private ArrayList<String> mDatas;
    private int mPosition;
    private int mLocationX;
    private int mLocationY;
    private int mWidth;
    private int mHeight;
    private SmoothImageView imageView;
    private ImageViewTouch imageViewTouch;
    private String mImageUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        init();
        super.onCreate(savedInstanceState);
        mTitleBar.hideTitleLayout();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int setView() {
        return R.layout.activity_spaceimagedetail;
    }

    @Override
    protected void findViews() {
        imageViewTouch = (ImageViewTouch) this.findViewById(R.id.id_touch_img);
        imageViewTouch.setOnLongClickListener(this);
        imageView = (SmoothImageView) this.findViewById(R.id.id_smooth_img);
        imageView.setOriginalInfo(mWidth, mHeight, mLocationX, mLocationY);
        imageView.transformIn();
//        imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        imageView.getLayoutParams().width = -1;
        imageView.getLayoutParams().height = -1;
        imageView.setScaleType(ScaleType.FIT_CENTER);
        ImageLoader.getInstance().displayImage(mImageUrl, imageView);
        imageView.setOnLongClickListener(this);
        imageView.setOnClickListener(this);
        ImageLoader.getInstance().setDefaultLoadingListener(new ImageLoaderDwonLoaderListenner());
    }


    @Override
    protected void initEvent() {

    }

    @Override
    public void init() {
//        mDatas = (ArrayList<String>) getIntent().getSerializableExtra("images");
        mImageUrl = getIntent().getStringExtra("imageUrl");
        mPosition = getIntent().getIntExtra("position", 0);
        mLocationX = getIntent().getIntExtra("locationX", 0);
        mLocationY = getIntent().getIntExtra("locationY", 0);
        mWidth = getIntent().getIntExtra("width", 0);
        mHeight = getIntent().getIntExtra("height", 0);
    }

    @Override
    protected void loadData() {

    }


    @Override
    public void onBackPressed() {
        imageView.setOnTransformListener(new SmoothImageView.TransformListener() {
            @Override
            public void onTransformComplete(int mode) {
                if (mode == 2) {
                    finish();
                }
            }
        });
        imageView.transformOut();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            overridePendingTransition(0, 0);
        }
    }


    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.id_smooth_img:
                imageView.setVisibility(View.GONE);
                imageViewTouch.setVisibility(View.VISIBLE);
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                imageViewTouch.setImageBitmap(bitmap);
                imageViewTouch.setDoubleTapEnabled(true);
                break;
            case R.id.id_touch_img:
                imageView.setVisibility(View.VISIBLE);
                imageViewTouch.setVisibility(View.GONE);
                break;
        }
        return false;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_touch_img:
                break;
        }
    }

    /**
     * EventBus 回调
     *
     * @param loaderDownloadEvent
     */
    public void onEventMainThread(EventFactory.ImageLoaderDownloadEvent loaderDownloadEvent) {
        Bitmap bitmap = loaderDownloadEvent.getBitmap();
        imageView.setVisibility(View.GONE);
        imageViewTouch.setVisibility(View.VISIBLE);
        imageViewTouch.setImageBitmap(bitmap);
        imageViewTouch.setDoubleTapEnabled(true);
    }

    public Handler mHanlder = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bitmap bitmap = (Bitmap) msg.obj;
            imageView.setVisibility(View.GONE);
            imageViewTouch.setVisibility(View.VISIBLE);
            imageViewTouch.setImageBitmap(bitmap);
            imageViewTouch.setDoubleTapEnabled(true);

        }
    };


    /**
     * universal 加载图片结果回调
     */
    public class ImageLoaderDwonLoaderListenner implements ImageLoadingListener {

        @Override
        public void onLoadingStarted(String s, View view) {

        }

        @Override
        public void onLoadingFailed(String s, View view, FailReason failReason) {

        }

        @Override
        public void onLoadingComplete(String s, View view, Bitmap bitmap) {
//            EventBus.getDefault().post(new EventFactory.ImageLoaderDownloadEvent().loaderComplete(bitmap));
            Message msg = Message.obtain();
            msg.obj = bitmap;
            mHanlder.handleMessage(msg);
        }

        @Override
        public void onLoadingCancelled(String s, View view) {

        }
    }

    @Override
    public void recreate() {
        super.recreate();
    }
}