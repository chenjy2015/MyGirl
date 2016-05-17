package app.originality.com.originality.ui.fragment;

import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import app.originality.com.originality.R;
import app.originality.com.originality.adapter.PhotoHomeRecyclerAdapter;
import app.originality.com.originality.bean.PhotoGroupVO;
import app.originality.com.originality.config.Contants;
import app.originality.com.originality.modules.photo.interfaces.RecyclerViewItemClickListenner;
import app.originality.com.originality.util.JumpManager;
import app.originality.com.originality.util.ToastUtils;
import app.originality.com.originality.util.media.MediaContact;
import app.originality.com.originality.util.media.MediaManager;
import app.originality.com.originality.util.media.MediaType;
import app.originality.com.originality.widget.recycler_divider_itemDecoration.HorizontalDividerItemDecoration;

import java.util.ArrayList;

public class PhotoFragment extends BaseFragment implements RecyclerViewItemClickListenner {

    private RecyclerView mRecyclerView;
    private ArrayList<PhotoGroupVO> mPhotoGroupVOs;
    private PhotoHomeRecyclerAdapter mPhotoHomeAdapter;

    public PhotoFragment() {
    }

    @Override
    protected int setView() {
        return R.layout.fragment_photo;
    }

    @Override
    protected void findViews() {
        mRecyclerView = (RecyclerView) this.findViewById(R.id.id_recyclerview);
    }

    @Override
    protected void initEvent() {
        MediaManager.getInstance().player(mAct, MediaType.MUSIC_RAW, MediaContact.AbsoluteMusic.MUSIC_ID_DRAGON_WARRIOR_RISE + "");
    }

    @Override
    protected void init() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mAct);
        mRecyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void loadData() {
        PhotoGroupVO p = new PhotoGroupVO();
        p.setGroupUrl(Contants.imageUrls[0]);
        p.setGroupDescription("深圳 2014.3.21");
        p.setGroupId("0");

        PhotoGroupVO p1 = new PhotoGroupVO();
        p1.setGroupUrl(Contants.imageUrls[1]);
        p1.setGroupDescription("桂林 2015.6.11");
        p1.setGroupId("1");

        PhotoGroupVO p2 = new PhotoGroupVO();
        p2.setGroupUrl(Contants.imageUrls[2]);
        p2.setGroupDescription("哈尔滨 2015.8.09");
        p2.setGroupId("2");

        PhotoGroupVO p3 = new PhotoGroupVO();
        p3.setGroupUrl(Contants.imageUrls[3]);
        p3.setGroupDescription("马来西亚 2015.11.16");
        p3.setGroupId("3");

        PhotoGroupVO p4 = new PhotoGroupVO();
        p4.setGroupUrl(Contants.imageUrls[4]);
        p4.setGroupDescription("纽约 2016.4.16");
        p4.setGroupId("4");

        mPhotoGroupVOs = new ArrayList<PhotoGroupVO>();
        mPhotoGroupVOs.add(p);
        mPhotoGroupVOs.add(p1);
        mPhotoGroupVOs.add(p2);
        mPhotoGroupVOs.add(p3);
        mPhotoGroupVOs.add(p4);

        mPhotoHomeAdapter = new PhotoHomeRecyclerAdapter(mAct, mPhotoGroupVOs, this);
        mRecyclerView.setAdapter(mPhotoHomeAdapter);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(mAct).build());
        Paint paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(getResources().getColor(R.color.light_gray));
        paint.setAntiAlias(true);
        paint.setPathEffect(new DashPathEffect(new float[]{25.0f, 25.0f}, 0));
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(mAct)
                .paint(paint)
                .showLastDivider()
                .build());
    }

    /**
     * item click listenner
     *
     * @param position
     * @param convertView
     */
    @Override
    public void onRecyclerViewClick(int position, View convertView) {
        JumpManager.jumpPhotoAlbumActivity(mAct, mPhotoGroupVOs.get(position));
        ToastUtils.showBigVioletToastOnBottom(mAct, "item" + position + " click ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MediaManager.getInstance().stop();
    }
}