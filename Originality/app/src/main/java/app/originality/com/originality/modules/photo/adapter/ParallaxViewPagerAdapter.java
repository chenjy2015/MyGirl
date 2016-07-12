package app.originality.com.originality.modules.photo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import app.originality.com.originality.R;
import app.originality.com.originality.modules.photo.bean.PhotoBeanVO;
import app.originality.com.originality.util.ToastUtils;
import app.originality.com.originality.util.animation.Rotate3dAnimation;
import app.originality.com.originality.widget.zoomimage.ImageViewTouch;

public class ParallaxViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    public List<PhotoBeanVO> mData;

    public ParallaxViewPagerAdapter(Context context, List<PhotoBeanVO> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_parallax_viewpager_adapter, null);
        //ImageViewTouch imageView = (ImageViewTouch) view.findViewById(R.id.id_touch_img);
        ViewHolder viewHolder = new ViewHolder(view, position);
        container.addView(view);
        return view;
    }

    public class ViewHolder {
        private RelativeLayout mLayout;
        private PhotoView mPhotoView;
        private LinearLayout mTextLayout;
        private TextView mTextView;

        public ViewHolder(View parentView, int position) {
            mLayout = (RelativeLayout) parentView.findViewById(R.id.id_layout);
            mTextLayout = (LinearLayout) parentView.findViewById(R.id.id_text_layout);
            mTextView = (TextView) parentView.findViewById(R.id.id_text);
            mPhotoView = (PhotoView) parentView.findViewById(R.id.id_photo_img);
            mPhotoView.enable();//开启放大缩小
            PhotoBeanVO photoBeanVO = mData.get(position);
            ImageLoader.getInstance().displayImage(photoBeanVO.getUrl(), mPhotoView);
            initEvent();
        }

        public void initEvent() {
            mPhotoView.setOnLongClickListener(new OnLongClick());
            mTextView.setOnClickListener(new OnClick());
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
}