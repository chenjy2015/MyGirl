package app.originality.com.originality.modules.photo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.nostra13.universalimageloader.core.ImageLoader;

import app.originality.com.originality.R;
import app.originality.com.originality.util.ToastUtils;
import app.originality.com.originality.util.animation.Rotate3dAnimation;

/**
 * @author cjy
 * @version V1.0
 * @company MyGril
 * @Description 展示单张图片界面
 * @date 2016/4/22
 */
public class SpaceImageDetailActivity2 extends Activity implements View.OnLongClickListener, View.OnClickListener {

    private RelativeLayout mLayout;
    private PhotoView mPhotoView;
    private LinearLayout mTextLayout;
    private TextView mTextView;

    public static Info photoViewInfo;
    public static String mImgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_image_detail2);
        mLayout = (RelativeLayout) this.findViewById(R.id.id_layout);
        mTextLayout = (LinearLayout) this.findViewById(R.id.id_text_layout);
        mTextView = (TextView) this.findViewById(R.id.id_text);
        mPhotoView = (PhotoView) this.findViewById(R.id.id_photo_img);
        init();
        initEvent();
    }

    public static void setInfo(Info info, String url) {
        photoViewInfo = info;
        mImgUrl = url;
    }

    public void init() {
        mPhotoView.animaFrom(photoViewInfo);
        mPhotoView.enable();//开启手势监听
        ImageLoader.getInstance().displayImage(mImgUrl, mPhotoView);
    }

    public void initEvent() {
        mPhotoView.setOnLongClickListener(new OnLongClick());
        mTextView.setOnClickListener(new OnClick());
    }


    @Override
    public void onBackPressed() {
        if(mPhotoView.getVisibility() == View.GONE){
            finish();
        }
        mPhotoView.animaTo(photoViewInfo, new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessageDelayed(-1, 100);
            }
        });
    }

    public Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == -1) {
                finish();
            }
        }
    };

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
                break;
        }
        return false;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    public class OnLongClick implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            // 获取布局的中心点位置，作为旋转的中心点
            float centerX = mLayout.getWidth() / 2f;
            float centerY = mLayout.getHeight() / 2f;
            // 构建3D旋转动画对象，旋转角度为0到90度，这使得ListView将会从可见变为不可见
            final Rotate3dAnimation rotation = new Rotate3dAnimation(0, 90, centerX, centerY,
                    310.0f, true);
            // 动画持续时间500毫秒
            rotation.setDuration(500);
            // 动画完成后保持完成的状态
            rotation.setFillAfter(true);
            rotation.setInterpolator(new AccelerateInterpolator());
            // 设置动画的监听器
            rotation.setAnimationListener(new TurnToTextView());
            mLayout.startAnimation(rotation);
            return false;
        }
    }

    public class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // 获取布局的中心点位置，作为旋转的中心点
            float centerX = mLayout.getWidth() / 2f;
            float centerY = mLayout.getHeight() / 2f;
            // 构建3D旋转动画对象，旋转角度为360到270度，这使得ImageView将会从可见变为不可见，并且旋转的方向是相反的
            final Rotate3dAnimation rotation = new Rotate3dAnimation(360, 270, centerX,
                    centerY, 310.0f, true);
            // 动画持续时间500毫秒
            rotation.setDuration(500);
            // 动画完成后保持完成的状态
            rotation.setFillAfter(true);
            rotation.setInterpolator(new AccelerateInterpolator());
            // 设置动画的监听器
            rotation.setAnimationListener(new TurnToImageView());
            mLayout.startAnimation(rotation);
        }
    }


    /**
     * 注册在ListView点击动画中的动画监听器，用于完成ListView的后续动画。
     *
     * @author guolin
     */
    class TurnToTextView implements Animation.AnimationListener {


        @Override
        public void onAnimationStart(Animation animation) {
        }

        /**
         * 当ListView的动画完成后，还需要再启动ImageView的动画，让ImageView从不可见变为可见
         */
        @Override
        public void onAnimationEnd(Animation animation) {
            // 获取布局的中心点位置，作为旋转的中心点
            float centerX = mLayout.getWidth() / 2f;
            float centerY = mLayout.getHeight() / 2f;
            // 将ListView隐藏
            mTextLayout.setVisibility(View.VISIBLE);
            // 将ImageView显示
            mPhotoView.setVisibility(View.GONE);
            mPhotoView.requestFocus();
            // 构建3D旋转动画对象，旋转角度为270到360度，这使得ImageView将会从不可见变为可见
            final Rotate3dAnimation rotation = new Rotate3dAnimation(270, 360, centerX, centerY,
                    310.0f, false);
            // 动画持续时间500毫秒
            rotation.setDuration(500);
            // 动画完成后保持完成的状态
            rotation.setFillAfter(true);
            rotation.setInterpolator(new AccelerateInterpolator());
            mLayout.startAnimation(rotation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

    }

    /**
     * 注册在ImageView点击动画中的动画监听器，用于完成ImageView的后续动画。
     *
     * @author guolin
     */
    class TurnToImageView implements Animation.AnimationListener {


        @Override
        public void onAnimationStart(Animation animation) {
        }

        /**
         * 当ImageView的动画完成后，还需要再启动ListView的动画，让ListView从不可见变为可见
         */
        @Override
        public void onAnimationEnd(Animation animation) {
            // 获取布局的中心点位置，作为旋转的中心点
            float centerX = mLayout.getWidth() / 2f;
            float centerY = mLayout.getHeight() / 2f;
            // 将ImageView隐藏
            mPhotoView.setVisibility(View.VISIBLE);
            // 将ListView显示
            mTextLayout.setVisibility(View.GONE);
            mTextLayout.requestFocus();
            // 构建3D旋转动画对象，旋转角度为90到0度，这使得ListView将会从不可见变为可见，从而回到原点
            final Rotate3dAnimation rotation = new Rotate3dAnimation(90, 0, centerX, centerY,
                    310.0f, false);
            // 动画持续时间500毫秒
            rotation.setDuration(500);
            // 动画完成后保持完成的状态
            rotation.setFillAfter(true);
            rotation.setInterpolator(new AccelerateInterpolator());
            mLayout.startAnimation(rotation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

    }
}