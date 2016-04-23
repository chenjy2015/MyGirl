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
import app.originality.com.originality.modules.photo.bean.PhotoBeanVO;
import app.originality.com.originality.util.AndroidSystemHelper;
import app.originality.com.originality.util.StringUtils;

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
        return convertView;
    }

    public class ViewHolder {
//        private ImageView mImg;
        private SimpleDraweeView mImg;
        private TextView mLabel;
        private TextView mCreateTimeText;
        private TextView mModifyTimeText;
        private TextView mPictureSize;
        private LinearLayout mDescriptionLayout;

        private ViewHolder(View view) {
            this.mImg = (SimpleDraweeView) view.findViewById(R.id.id_photo_img);
//            this.mImg = (ImageView) view.findViewById(R.id.id_photo_img);
            this.mLabel = (TextView) view.findViewById(R.id.id_photo_label);
            this.mCreateTimeText = (TextView) view.findViewById(R.id.id_photo_create_time);
            this.mModifyTimeText = (TextView) view.findViewById(R.id.id_photo_modify_time);
            this.mPictureSize = (TextView) view.findViewById(R.id.id_photo_size);
            this.mDescriptionLayout = (LinearLayout) view.findViewById(R.id.id_img_description_layout);
        }
    }


    private final Random mRandom;

    private int getPositionRatio(int position) {
        int totalHeight = AndroidSystemHelper.dp2px(mRandom.nextInt(100 + position), mAct);
        return totalHeight;
    }
}