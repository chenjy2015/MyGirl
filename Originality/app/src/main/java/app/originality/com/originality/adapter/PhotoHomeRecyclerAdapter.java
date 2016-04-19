package app.originality.com.originality.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.DraweeView;

import app.originality.com.originality.R;
import app.originality.com.originality.bean.PhotoGroupVO;
import app.originality.com.originality.modules.photo.interfaces.RecyclerViewItemClickListenner;
import app.originality.com.originality.util.StringUtils;

import java.util.List;
import java.util.ArrayList;

/**
 * @author cjy
 * @version V1.0
 * @company Chenjy_Studio
 * @Description 相册列表适配器
 * @date 2016/4/18
 */
public class PhotoHomeRecyclerAdapter extends RecyclerView.Adapter {

    private List<PhotoGroupVO> mData;
    private Context mContext;
    private RecyclerViewItemClickListenner recyclerViewItemClickListenner;

    public PhotoHomeRecyclerAdapter(Context context, List<PhotoGroupVO> data, RecyclerViewItemClickListenner recyclerViewItemClickListenner) {
        this.mContext = context;
        this.mData = data;
        this.recyclerViewItemClickListenner = recyclerViewItemClickListenner;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_photo_home, parent, false);
        return new PhotoHomeHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        PhotoGroupVO photoGroupVO = mData.get(position);
        final PhotoHomeHolder homeHolder = (PhotoHomeHolder) holder;
        Uri uri = Uri.parse(photoGroupVO.getGroupUrl());
        homeHolder.albumImg.setImageURI(uri);
        homeHolder.albumText.setText(StringUtils.checkStringIsNull(photoGroupVO.getGroupDescription()));
        homeHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewItemClickListenner.onRecyclerViewClick(position, homeHolder.view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mData.size();
    }


    class PhotoHomeHolder extends RecyclerView.ViewHolder {
        public DraweeView albumImg;
        public TextView albumText;
        public View view;

        public PhotoHomeHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.albumImg = (DraweeView) itemView.findViewById(R.id.id_album_img);
            this.albumText = (TextView) itemView.findViewById(R.id.id_album_text);
        }
    }
}