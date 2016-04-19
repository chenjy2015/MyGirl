package app.originality.com.originality.modules.photo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yalantis.flipviewpager.utils.FlipSettings;


import app.originality.com.originality.R;
import app.originality.com.originality.bean.PhotoGroupVO;
import app.originality.com.originality.config.Contants;
import app.originality.com.originality.modules.photo.adapter.FriendsAdapter;
import app.originality.com.originality.modules.photo.adapter.PhotoListAdapter;
import app.originality.com.originality.modules.photo.bean.PhotoBeanVO;
import app.originality.com.originality.modules.photo.utils.Utils;
import app.originality.com.originality.ui.BaseActivity;
import app.originality.com.originality.util.DataTimeUtils;
import app.originality.com.originality.widget.staggeredgridview.StaggeredGridView;

import java.util.ArrayList;

public class PhotoListActivity extends BaseActivity {

    private StaggeredGridView mStaggeredGridView;
    private PhotoListAdapter mPhotoListAdapter;
    private ArrayList<PhotoBeanVO> mPhotoBeanVOs;
    private PhotoGroupVO mPhotoGroupVO;


    @Override
    protected int setView() {
        return R.layout.activity_photo_list;
    }

    @Override
    protected void findViews() {
        mStaggeredGridView = (StaggeredGridView) this.findViewById(R.id.id_photo_gridview);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {
//        FlipSettings settings = new FlipSettings.Builder().defaultPage(1).build();
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.setAdapter(new FriendsAdapter(PhotoListActivity.this, Utils.friends, settings));


    }

    @Override
    protected void loadData() {
        mPhotoBeanVOs = new ArrayList<PhotoBeanVO>();
        for (int i = 0; i < Contants.imageUrls.length; i++) {
            PhotoBeanVO photoBeanVO = new PhotoBeanVO();
            photoBeanVO.setCreateTime(DataTimeUtils.getStringDate(System.currentTimeMillis()));
            photoBeanVO.setGroupId(mPhotoGroupVO.getGroupId());
            photoBeanVO.setGroupName(mPhotoGroupVO.getGroupDescription());
            photoBeanVO.setLabel(" 风景 " + i);
            photoBeanVO.setUrl(Contants.imageUrls[i]);
            mPhotoBeanVOs.add(photoBeanVO);
        }
        mPhotoListAdapter = new PhotoListAdapter(PhotoListActivity.this, mPhotoBeanVOs);
        mStaggeredGridView.setAdapter(mPhotoListAdapter);
    }


}
