package app.originality.com.originality.modules.photo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.etsy.android.grid.StaggeredGridView;
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
import app.originality.com.originality.util.ToastUtils;

import java.util.ArrayList;

public class PhotoListActivity extends BaseActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private StaggeredGridView mStaggeredGridView;
    private PhotoListAdapter mPhotoListAdapter;
    private ArrayList<PhotoBeanVO> mPhotoBeanVOs;
    private PhotoGroupVO mPhotoGroupVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        loadData();
    }

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

        mPhotoGroupVO = (PhotoGroupVO) getIntent().getSerializableExtra("PhotoGroupVO");

    }

    @Override
    protected void loadData() {
        mPhotoBeanVOs = new ArrayList<PhotoBeanVO>();
        for (int i = 0; i < Contants.imageUrls.length; i++) {
            PhotoBeanVO photoBeanVO = new PhotoBeanVO();
            photoBeanVO.setCreateTime(DataTimeUtils.getStringDate(System.currentTimeMillis()));
            photoBeanVO.setGroupId(mPhotoGroupVO.getGroupId());
            photoBeanVO.setGroupName(mPhotoGroupVO.getGroupDescription());
//            photoBeanVO.setLabel(" 风景 " + fliter(Contants.imageUrls.length, i));
            photoBeanVO.setLabel("风景" + (i + 1));
            photoBeanVO.setUrl(Contants.imageUrls[i]);
            mPhotoBeanVOs.add(photoBeanVO);
        }
        mPhotoListAdapter = new PhotoListAdapter(PhotoListActivity.this, mPhotoBeanVOs);
        mStaggeredGridView.setAdapter(mPhotoListAdapter);
        mStaggeredGridView.setOnItemClickListener(this);
        mStaggeredGridView.setOnItemLongClickListener(this);
    }


    private String fliter(int size, int count) {
        StringBuilder builder = new StringBuilder();
        for (int j = size, max = (count * 1234) % 500; j < max; j++)
            builder.append(count).append(' ');
        return builder.toString();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtils.showBigVioletToastOnBottom(getApplicationContext(), "item " + position + "click");
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtils.showBigVioletToastOnBottom(getApplicationContext(), "item " + position + "long click");
        return true;
    }
}
