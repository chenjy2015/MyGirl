package app.originality.com.originality.modules.photo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.etsy.android.grid.StaggeredGridView;
import com.facebook.drawee.view.SimpleDraweeView;


import app.originality.com.originality.R;
import app.originality.com.originality.bean.PhotoGroupVO;
import app.originality.com.originality.bean.SpaceImageVO;
import app.originality.com.originality.config.Contants;
import app.originality.com.originality.factory.OYEventFactory;
import app.originality.com.originality.modules.photo.adapter.PhotoListAdapter;
import app.originality.com.originality.modules.photo.bean.PhotoBeanVO;
import app.originality.com.originality.modules.photo.views.ScrollDialog;
import app.originality.com.originality.ui.BaseActivity;
import app.originality.com.originality.util.DataTimeUtils;
import app.originality.com.originality.util.JumpManager;
import app.originality.com.originality.util.LogOut;
import de.greenrobot.event.EventBus;

import java.util.ArrayList;

/**
 * @author cjy
 * @version V1.0
 * @company Chenjy_Studio
 * @Description 纵向浏览图片模式
 * @date 2016/6/30 14:55
 */
public class VerticalDisplayPhotoListActivity extends BaseActivity {

    private StaggeredGridView mStaggeredGridView;
    private PhotoListAdapter mPhotoListAdapter;
    private ArrayList<PhotoBeanVO> mPhotoBeanVOs;
    private PhotoGroupVO mPhotoGroupVO;

    @Override
    public void preCreate() {
        super.preCreate();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 点击事件回调
     *
     * @param event
     */
    public void onEventMainThread(OYEventFactory.OnItemClickTouchEvent event) {
//        SpaceImageVO spaceImageVO = initSpaceImageVO(event.position, event.view);
//        JumpManager.jumpSpaceImageActivity(this, spaceImageVO);
    }

    /**
     * 长按事件回调
     *
     * @param event
     */
    public void onEventMainThread(OYEventFactory.OnItemLongClickTouchEvent event) {

    }


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
        mPhotoGroupVO = (PhotoGroupVO) getIntent().getSerializableExtra("PhotoGroupVO");
        mTitleBar.setRightText("横向浏览");
        mTitleBar.setRightLayoutOnClickListenner(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpManager.jumpHorizontalDisplayPhotoListActivity(VerticalDisplayPhotoListActivity.this, mPhotoGroupVO, null);
            }
        });
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
        mPhotoListAdapter = new PhotoListAdapter(VerticalDisplayPhotoListActivity.this, mPhotoBeanVOs);
        mStaggeredGridView.setAdapter(mPhotoListAdapter);
//        mStaggeredGridView.setOnItemClickListener(this);
//        mStaggeredGridView.setOnItemLongClickListener(this);
    }


    public void showScrollDialog(int position, View view) {
        ScrollDialog scrollDialog = new ScrollDialog(VerticalDisplayPhotoListActivity.this);
        scrollDialog.show();
        scrollDialog.setImageUrl(Contants.imageUrls[position]);
//        SimpleDraweeView imageView = (SimpleDraweeView) view.findViewById(R.id.id_photo_img);
        ImageView imageView = (ImageView) view.findViewById(R.id.id_photo_img);
        int[] location = new int[2];
        imageView.getLocationOnScreen(location);
        scrollDialog.setSommthImage(location[0], location[1], imageView.getMeasuredWidth(), imageView.getMeasuredHeight());
    }


    /**
     * 提取 图片信息
     *
     * @param position
     * @param parentView
     * @return
     */
    public SpaceImageVO initSpaceImageVO(int position, View parentView) {
//        SimpleDraweeView imageView = (SimpleDraweeView) parentView.findViewById(R.id.id_photo_img);
        SimpleDraweeView imageView = (SimpleDraweeView) mPhotoListAdapter.getView(position, parentView, mStaggeredGridView).findViewById(R.id.id_photo_img);
        int[] location = new int[2];
        imageView.getLocationOnScreen(location);
        SpaceImageVO spaceImageVO = new SpaceImageVO();
        spaceImageVO.setImageUrl(Contants.imageUrls[position]);
        spaceImageVO.setPosition(position);
        spaceImageVO.setLocationX(location[0]);
        spaceImageVO.setLocationY(location[1]);
        spaceImageVO.setWidth(imageView.getWidth());
        spaceImageVO.setHeight(imageView.getHeight());
        return spaceImageVO;
    }


}
