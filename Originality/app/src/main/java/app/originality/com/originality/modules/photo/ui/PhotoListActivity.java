package app.originality.com.originality.modules.photo.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.etsy.android.grid.StaggeredGridView;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yalantis.flipviewpager.utils.FlipSettings;


import app.originality.com.originality.R;
import app.originality.com.originality.application.OAHelper;
import app.originality.com.originality.bean.PhotoGroupVO;
import app.originality.com.originality.config.Contants;
import app.originality.com.originality.modules.photo.adapter.FriendsAdapter;
import app.originality.com.originality.modules.photo.adapter.PhotoListAdapter;
import app.originality.com.originality.modules.photo.bean.PhotoBeanVO;
import app.originality.com.originality.modules.photo.utils.Utils;
import app.originality.com.originality.modules.photo.views.ScrollDialog;
import app.originality.com.originality.ui.BaseActivity;
import app.originality.com.originality.util.AndroidSystemHelper;
import app.originality.com.originality.util.BitmapUtils;
import app.originality.com.originality.util.DataTimeUtils;
import app.originality.com.originality.util.ToastUtils;

import java.io.ByteArrayOutputStream;
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
        //ToastUtils.showBigVioletToastOnBottom(getApplicationContext(), "item " + position + "click");
        JumpSpaceImageDetailActivity(mPhotoListAdapter.getView(position, view, parent), position);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//        ToastUtils.showBigVioletToastOnBottom(getApplicationContext(), "item " + position + "long click");
//        showScrollDialog(position, mPhotoListAdapter.getView(position, view, parent));
        return true;
    }

    public void showScrollDialog(int position, View view) {
        ScrollDialog scrollDialog = new ScrollDialog(PhotoListActivity.this);
        scrollDialog.show();
        scrollDialog.setImageUrl(Contants.imageUrls[position]);
//        SimpleDraweeView imageView = (SimpleDraweeView) view.findViewById(R.id.id_photo_img);
        ImageView imageView = (ImageView) view.findViewById(R.id.id_photo_img);
        int[] location = new int[2];
        imageView.getLocationOnScreen(location);
        scrollDialog.setSommthImage(location[0], location[1], imageView.getMeasuredWidth(), imageView.getMeasuredHeight());
    }

    public void JumpSpaceImageDetailActivity(View view, int position) {
        Intent intent = new Intent(PhotoListActivity.this, SpaceImageDetailActivity.class);
        intent.putExtra("imageUrl", Contants.imageUrls[position]);
        intent.putExtra("position", position);

//        ImageView imageView = (ImageView) view.findViewById(R.id.id_photo_img);
        SimpleDraweeView imageView = (SimpleDraweeView) view.findViewById(R.id.id_photo_img);
        int[] location = new int[2];
        imageView.getLocationOnScreen(location);
        intent.putExtra("locationX", location[0]);
        intent.putExtra("locationY", location[1]);
        intent.putExtra("width", imageView.getWidth());
        intent.putExtra("height", imageView.getHeight());
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

}
