package com.umeng.share.UmengSharedApplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.share.UmengSharedApplication.R;
import com.umeng.share.UmengSharedApplication.bean.PlatformVO;


import java.util.List;

public class SharePlatformAdapter extends BaseShareAdapter {


    public SharePlatformAdapter(Activity act, List<PlatformVO> list) {
        super(act, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mAct).inflate(R.layout.item_share_adapter, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PlatformVO platformVO = (PlatformVO) mList.get(position);
        viewHolder.icon.setImageResource(platformVO.getIcon());
        viewHolder.description.setText(platformVO.getDescription());
        return convertView;
    }

    private class ViewHolder {
        private ImageView icon;
        private TextView description;

        public ViewHolder(View convertView) {
            icon = (ImageView) convertView.findViewById(R.id.id_share_icon);
            description = (TextView) convertView.findViewById(R.id.id_share_text);
        }
    }
}