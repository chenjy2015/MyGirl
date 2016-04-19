package app.originality.com.originality.modules.photo.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;

import app.originality.com.originality.R;
import app.originality.com.originality.adapter.OriginalityBaseAdapter;
import app.originality.com.originality.config.Contants;
import app.originality.com.originality.modules.photo.bean.PhotoBeanVO;

import java.util.List;
import java.util.ArrayList;

public class PhotoListAdapter extends OriginalityBaseAdapter {

    private Context mContext;
    private List<PhotoBeanVO> mData;

    public PhotoListAdapter(Activity act, List data) {
        super(act, data);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_photo_list, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PhotoBeanVO photoBeanVO = mData.get(position);
        Uri uri = Uri.parse(photoBeanVO.getUrl());
        viewHolder.mImg.setImageURI(uri);

        return convertView;
    }

    public class ViewHolder {
        private SimpleDraweeView mImg;
        private TextView mLabel;
        private TextView mCreateTimeText;
        private TextView mModifyTimeText;
        private TextView mPictureSize;

        private ViewHolder(View view) {
            this.mImg = (SimpleDraweeView) view.findViewById(R.id.id_photo_img);
            this.mLabel = (TextView) view.findViewById(R.id.id_photo_label);
            this.mCreateTimeText = (TextView) view.findViewById(R.id.id_photo_create_time);
            this.mModifyTimeText = (TextView) view.findViewById(R.id.id_photo_modify_time);
            this.mPictureSize = (TextView) view.findViewById(R.id.id_photo_size);
        }

    }
}