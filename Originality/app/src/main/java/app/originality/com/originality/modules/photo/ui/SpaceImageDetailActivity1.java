package app.originality.com.originality.modules.photo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.etsy.android.grid.StaggeredGridView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.security.acl.Group;

import app.originality.com.originality.R;
import app.originality.com.originality.bean.SpaceImageVO;
import app.originality.com.originality.ui.BaseActivity;
import app.originality.com.originality.widget.zoomimage.SmoothImageView;
import de.greenrobot.event.EventBus;

/**
 * @author cjy
 * @version V1.0
 * @company MyGril
 * @Description 展示单张图片界面
 * @date 2016/4/22
 */
public class SpaceImageDetailActivity1 extends Activity implements View.OnLongClickListener, View.OnClickListener {

    private SmoothImageView imageView;
    private SpaceImageVO mSpaceImageVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        mSpaceImageVO = (SpaceImageVO) getIntent().getSerializableExtra("SpaceImageVO");
        imageView = new SmoothImageView(this);
        imageView.setOriginalInfo(mSpaceImageVO.getWidth(), mSpaceImageVO.getHeight(), mSpaceImageVO.getLocationX(), mSpaceImageVO.getLocationY());
        imageView.transformIn();
        imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        imageView.setScaleType(ScaleType.FIT_CENTER);
        ImageLoader.getInstance().displayImage(mSpaceImageVO.getImageUrl(), imageView);
        imageView.setOnLongClickListener(this);
        imageView.setOnClickListener(this);
        setContentView(imageView);
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
    }

}