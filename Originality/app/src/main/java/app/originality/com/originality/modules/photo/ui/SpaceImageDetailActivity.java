package app.originality.com.originality.modules.photo.ui;

import android.os.Bundle;

import app.originality.com.originality.R;
import app.originality.com.originality.bean.SpaceImageVO;
import app.originality.com.originality.ui.BaseActivity;
import app.originality.com.originality.widget.zoomimage.SmoothImageView;
import de.greenrobot.event.EventBus;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @author cjy
 * @version V1.0
 * @company MyGril
 * @Description 展示单张图片界面
 * @date 2016/4/22
 */
public class SpaceImageDetailActivity extends BaseActivity implements View.OnLongClickListener, View.OnClickListener {

    private SmoothImageView imageView;
    private SpaceImageVO mSpaceImageVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        mTitleBar.hideTitleLayout();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int setView() {
        return R.layout.activity_spaceimagedetail;
    }

    @Override
    protected void findViews() {
        imageView = (SmoothImageView) this.findViewById(R.id.id_smooth_img);
    }


    @Override
    protected void initEvent() {

    }

    @Override
    public void init() {
        mSpaceImageVO = (SpaceImageVO) getIntent().getSerializableExtra("SpaceImageVO");
        imageView.setOriginalInfo(mSpaceImageVO.getWidth(), mSpaceImageVO.getHeight(), mSpaceImageVO.getLocationX(), mSpaceImageVO.getLocationY());
        imageView.transformIn();
        imageView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        imageView.getLayoutParams().width = -1;
        imageView.getLayoutParams().height = -1;
        imageView.setScaleType(ScaleType.FIT_CENTER);
        ImageLoader.getInstance().displayImage(mSpaceImageVO.getImageUrl(), imageView);
        imageView.setOnLongClickListener(this);
        imageView.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }


    @Override
    public void onBackPressed() {
        imageView.setOnTransformListener(new SmoothImageView.TransformListener() {
            @Override
            public void onTransformComplete(int mode) {
                if (mode == 2) {
                    finish();
                }
            }
        });
        imageView.transformOut();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            overridePendingTransition(0, 0);
        }
    }


    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.id_smooth_img:
                break;
        }
        return false;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_touch_img:
                break;
        }
    }

}