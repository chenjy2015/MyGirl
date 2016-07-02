package app.originality.com.originality.modules.photo.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.ImageLoader;

import app.originality.com.originality.R;
import app.originality.com.originality.adapter.OriginalityBaseAdapter;
import app.originality.com.originality.config.Contants;
import app.originality.com.originality.factory.EventFactory;
import app.originality.com.originality.modules.photo.bean.PhotoBeanVO;
import app.originality.com.originality.util.AndroidSystemHelper;
import app.originality.com.originality.util.StringUtils;
import app.originality.com.originality.util.animation.Rotate3dAnimation;
import de.greenrobot.event.EventBus;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class PhotoListAdapter extends OriginalityBaseAdapter {


    public PhotoListAdapter(Activity act, List data) {
        super(act, data);
        mRandom = new Random();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mAct).inflate(R.layout.item_photo_list, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //动态随机设定 图片高度
        int positionHeight = getPositionRatio(position);
        int height = viewHolder.mImg.getLayoutParams().height;
        //对比当前图片高度 限制其不能大于200dp
        viewHolder.mImg.getLayoutParams().height = height > AndroidSystemHelper.dp2px(200, mAct) ? height : height + positionHeight;

        PhotoBeanVO photoBeanVO = (PhotoBeanVO) mData.get(position);
        Uri uri = Uri.parse(photoBeanVO.getUrl());
        viewHolder.mImg.setImageURI(uri);
//        ImageLoader.getInstance().displayImage(photoBeanVO.getUrl(),viewHolder.mImg);

        viewHolder.mLabel.setText(StringUtils.checkStringIsNull(photoBeanVO.getLabel()));
        viewHolder.mCreateTimeText.setText(StringUtils.checkStringIsNull(photoBeanVO.getCreateTime()));
        viewHolder.mModifyTimeText.setText(StringUtils.checkStringIsNull(photoBeanVO.getModifyTime()));

        viewHolder.mImgLayout.setOnLongClickListener(new OnLongClick(viewHolder, 0));
        viewHolder.mDescriptionLayout.setOnLongClickListener(new OnLongClick(viewHolder, 1));
        viewHolder.mImg.setOnClickListener(new OnClick(position, convertView));
        return convertView;
    }

    public class OnClick implements View.OnClickListener {

        private int position;
        private View view;

        public OnClick(int position, View view) {
            this.position = position;
            this.view = view;
        }

        @Override
        public void onClick(View v) {
            EventBus.getDefault().post(new EventFactory.OnItemClickTouchEvent(position, view));
        }
    }

    /**
     * 长按事件
     */
    public class OnLongClick implements View.OnLongClickListener {

        private ViewHolder viewHolder;
        private int type;

        public OnLongClick(ViewHolder viewHolder, int type) {
            this.viewHolder = viewHolder;
            this.type = type;
        }

        @Override
        public boolean onLongClick(View v) {
            if (type == 0) {
                // 获取布局的中心点位置，作为旋转的中心点
                float centerX = viewHolder.mLayout.getWidth() / 2f;
                float centerY = viewHolder.mLayout.getHeight() / 2f;
                // 构建3D旋转动画对象，旋转角度为0到90度，这使得ListView将会从可见变为不可见
                final Rotate3dAnimation rotation = new Rotate3dAnimation(0, 90, centerX, centerY,
                        310.0f, true);
                // 动画持续时间500毫秒
                rotation.setDuration(500);
                // 动画完成后保持完成的状态
                rotation.setFillAfter(true);
                rotation.setInterpolator(new AccelerateInterpolator());
                // 设置动画的监听器
                rotation.setAnimationListener(new TurnToImageView(viewHolder));
                viewHolder.mLayout.startAnimation(rotation);
            } else {
                // 获取布局的中心点位置，作为旋转的中心点
                float centerX = viewHolder.mLayout.getWidth() / 2f;
                float centerY = viewHolder.mLayout.getHeight() / 2f;
                // 构建3D旋转动画对象，旋转角度为360到270度，这使得ImageView将会从可见变为不可见，并且旋转的方向是相反的
                final Rotate3dAnimation rotation = new Rotate3dAnimation(360, 270, centerX,
                        centerY, 310.0f, true);
                // 动画持续时间500毫秒
                rotation.setDuration(500);
                // 动画完成后保持完成的状态
                rotation.setFillAfter(true);
                rotation.setInterpolator(new AccelerateInterpolator());
                // 设置动画的监听器
                rotation.setAnimationListener(new TurnToListView(viewHolder));
                viewHolder.mLayout.startAnimation(rotation);
            }

            return false;
        }
    }


    public class ViewHolder {
        //        private ImageView mImg;

        private SimpleDraweeView mImg;
        private TextView mLabel;
        private TextView mCreateTimeText;
        private TextView mModifyTimeText;
        private TextView mPictureSize;
        private LinearLayout mDescriptionLayout;
        private LinearLayout mImgLayout;
        private LinearLayout mLayout;

        private ViewHolder(View view) {
            this.mImg = (SimpleDraweeView) view.findViewById(R.id.id_photo_img);
//            this.mImg = (ImageView) view.findViewById(R.id.id_photo_img);
            this.mLabel = (TextView) view.findViewById(R.id.id_photo_label);
            this.mCreateTimeText = (TextView) view.findViewById(R.id.id_photo_create_time);
            this.mModifyTimeText = (TextView) view.findViewById(R.id.id_photo_modify_time);
            this.mPictureSize = (TextView) view.findViewById(R.id.id_photo_size);
            this.mDescriptionLayout = (LinearLayout) view.findViewById(R.id.id_img_description_layout);
            this.mImgLayout = (LinearLayout) view.findViewById(R.id.id_img_layout);
            this.mLayout = (LinearLayout) view.findViewById(R.id.id_layout);
        }
    }


    private final Random mRandom;

    private int getPositionRatio(int position) {
        int totalHeight = AndroidSystemHelper.dp2px(mRandom.nextInt(100 + position), mAct);
        return totalHeight;
    }


    /**
     * 注册在ListView点击动画中的动画监听器，用于完成ListView的后续动画。
     *
     * @author guolin
     */
    class TurnToImageView implements Animation.AnimationListener {

        private ViewHolder viewHolder;

        public TurnToImageView(ViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

        /**
         * 当ListView的动画完成后，还需要再启动ImageView的动画，让ImageView从不可见变为可见
         */
        @Override
        public void onAnimationEnd(Animation animation) {
            // 获取布局的中心点位置，作为旋转的中心点
            float centerX = viewHolder.mLayout.getWidth() / 2f;
            float centerY = viewHolder.mLayout.getHeight() / 2f;
            // 将ListView隐藏
            viewHolder.mDescriptionLayout.setVisibility(View.GONE);
            // 将ImageView显示
            viewHolder.mImgLayout.setVisibility(View.VISIBLE);
            viewHolder.mImgLayout.requestFocus();
            // 构建3D旋转动画对象，旋转角度为270到360度，这使得ImageView将会从不可见变为可见
            final Rotate3dAnimation rotation = new Rotate3dAnimation(270, 360, centerX, centerY,
                    310.0f, false);
            // 动画持续时间500毫秒
            rotation.setDuration(500);
            // 动画完成后保持完成的状态
            rotation.setFillAfter(true);
            rotation.setInterpolator(new AccelerateInterpolator());
            viewHolder.mLayout.startAnimation(rotation);
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
    class TurnToListView implements Animation.AnimationListener {


        private ViewHolder viewHolder;

        public TurnToListView(ViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }


        @Override
        public void onAnimationStart(Animation animation) {
        }

        /**
         * 当ImageView的动画完成后，还需要再启动ListView的动画，让ListView从不可见变为可见
         */
        @Override
        public void onAnimationEnd(Animation animation) {
            // 获取布局的中心点位置，作为旋转的中心点
            float centerX = viewHolder.mLayout.getWidth() / 2f;
            float centerY = viewHolder.mLayout.getHeight() / 2f;
            // 将ImageView隐藏
            viewHolder.mImgLayout.setVisibility(View.GONE);
            // 将ListView显示
            viewHolder.mDescriptionLayout.setVisibility(View.VISIBLE);
            viewHolder.mDescriptionLayout.requestFocus();
            // 构建3D旋转动画对象，旋转角度为90到0度，这使得ListView将会从不可见变为可见，从而回到原点
            final Rotate3dAnimation rotation = new Rotate3dAnimation(90, 0, centerX, centerY,
                    310.0f, false);
            // 动画持续时间500毫秒
            rotation.setDuration(500);
            // 动画完成后保持完成的状态
            rotation.setFillAfter(true);
            rotation.setInterpolator(new AccelerateInterpolator());
            viewHolder.mLayout.startAnimation(rotation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

    }

}