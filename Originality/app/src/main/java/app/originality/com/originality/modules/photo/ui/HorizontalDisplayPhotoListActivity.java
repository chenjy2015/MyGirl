package app.originality.com.originality.modules.photo.ui;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;

import app.originality.com.originality.R;
import app.originality.com.originality.bean.PhotoGroupVO;
import app.originality.com.originality.bean.SpaceImageVO;
import app.originality.com.originality.config.Contants;
import app.originality.com.originality.modules.photo.adapter.ParallaxViewPagerAdapter;
import app.originality.com.originality.modules.photo.bean.PhotoBeanVO;
import app.originality.com.originality.ui.BaseActivity;
import app.originality.com.originality.util.DataTimeUtils;
import app.originality.com.originality.util.JumpManager;
import app.originality.com.originality.widget.zoomimage.SmoothImageView;
import de.greenrobot.event.EventBus;
import share.umeng.com.parallax_viewpager_library.ParallaxViewPager;

import android.os.Handler;

import java.util.ArrayList;

/**
 * @author cjy
 * @version V1.0
 * @company MyGril
 * @Description 横向浏览图片模式
 * @date 2016/4/22
 */
public class HorizontalDisplayPhotoListActivity extends BaseActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private SmoothImageView mSmoothImageView;
    private SpaceImageVO mSpaceImageVO;
    private ParallaxViewPager mParallaxViewPager;
    private ParallaxViewPagerAdapter mPagerAdapter;
    private int mCurrentItem;               //当前页
    //    public List<SpaceImageVO> mData;
    private ArrayList<PhotoBeanVO> mPhotoBeanVOs;
    private PhotoGroupVO mPhotoGroupVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initEvent();
        loadData();
    }

    @Override
    protected int setView() {
        return R.layout.activity_space_image_detail;
    }

    @Override
    protected void findViews() {
        mSmoothImageView = (SmoothImageView) findViewById(R.id.id_smooth_img);
        mParallaxViewPager = (ParallaxViewPager) findViewById(R.id.id_parallax_viewpager);
    }

    @Override
    protected void init() {
        mTitleBar.setRightText("纵向浏览");
        mTitleBar.setRightLayoutOnClickListenner(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpManager.jumpVerticalDisplayPhotoListActivity(HorizontalDisplayPhotoListActivity.this, mPhotoGroupVO);
            }
        });
        mPhotoGroupVO = (PhotoGroupVO) getIntent().getSerializableExtra("PhotoGroupVO");
        mSpaceImageVO = (SpaceImageVO) getIntent().getSerializableExtra("SpaceImageVO");
    }


    @Override
    protected void initEvent() {
        if (mSpaceImageVO == null) {
            updateUI(1);
            return;
        }
        mSmoothImageView.setOriginalInfo(mSpaceImageVO.getWidth(), mSpaceImageVO.getHeight(), mSpaceImageVO.getLocationX(), mSpaceImageVO.getLocationY());
        mSmoothImageView.transformIn();
        mSmoothImageView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
//        mSmoothImageView.getLayoutParams().width = -1;
//        mSmoothImageView.getLayoutParams().height = -1;
        mSmoothImageView.setScaleType(ScaleType.FIT_CENTER);
        ImageLoader.getInstance().displayImage(mSpaceImageVO.getImageUrl(), mSmoothImageView);
        mSmoothImageView.setOnTransformListener(new SmoothImageView.TransformListener() {
            @Override
            public void onTransformComplete(int mode) {
                if (mode == 1) {
                    updateUI(1);
                }
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
        mHanlder.sendEmptyMessage(0);
    }

    public Handler mHanlder = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            initViewPager();
        }
    };

    public void updateUI(int type) {
        if (type == 0) {
            mSmoothImageView.setVisibility(View.VISIBLE);
            mParallaxViewPager.setVisibility(View.GONE);
        } else {
            mSmoothImageView.setVisibility(View.GONE);
            mParallaxViewPager.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        if (mSpaceImageVO == null) {
            finish();
            return;
        }
        updateUI(0);
        mSmoothImageView.setOnTransformListener(new SmoothImageView.TransformListener() {
            @Override
            public void onTransformComplete(int mode) {
                if (mode == 2) {
                    finish();
                }
            }
        });
        mSmoothImageView.transformOut();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            overridePendingTransition(0, 0);
        }
    }

    /**
     * 初始化翻页数据
     */
    private void initViewPager() {
        mPagerAdapter = new ParallaxViewPagerAdapter(this, mPhotoBeanVOs);
        mParallaxViewPager.setAdapter(mPagerAdapter);
        mParallaxViewPager.setCurrentItem(mCurrentItem);//设置当前页
        mParallaxViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }



}