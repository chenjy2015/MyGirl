package app.originality.com.originality.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import app.originality.com.originality.R;
import app.originality.com.originality.adapter.GuidePageAdapter;
import app.originality.com.originality.config.Contants;
import app.originality.com.originality.storage.OSPUtils;
import app.originality.com.originality.util.JumpManager;
import app.originality.com.originality.util.StringHelper;

/**
 * @author cjy
 * @version V1.0
 * @company Chenjy_Studio
 * @Description 首次安装时的引导界面
 * @date 2016/4/1
 */
public class GuidePageActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private TextView mHintText;
    private TextView mEnterText;
    private GuidePageAdapter mGuidePageAdapter;
    private ArrayList<View> mViews;
    private ArrayList<Integer> mResources;
    private String[] mGuideHintContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initEvent();
        loadData();
        setAdapter();
    }

    @Override
    protected int setView() {
        return R.layout.activity_guide_page;
    }

    @Override
    protected void findViews() {
        mViewPager = (ViewPager) this.findViewById(R.id.id_view_pager);
        mHintText = (TextView) this.findViewById(R.id.id_guide_text);
        mEnterText = (TextView) this.findViewById(R.id.id_guide_enter_text);

    }

    @Override
    protected void initEvent() {
        mEnterText.setOnClickListener(this);
    }

    @Override
    protected void init() {
        mTitleBar.hideTitleLayout();
        mGuideHintContent = StringHelper.getGuideHintContent(GuidePageActivity.this);
        mHintText.setText(mGuideHintContent[0]);
        mViews = new ArrayList<View>();
        mResources = new ArrayList<Integer>();
        mResources.add(R.mipmap.scenery);
        mResources.add(R.mipmap.scenery2);
        mResources.add(R.mipmap.scenery3);
    }

    @Override
    protected void loadData() {
        for (int i = 0; i < mResources.size(); i++) {
            SimpleDraweeView draweeView = new SimpleDraweeView(GuidePageActivity.this);
            Uri uri = Uri.parse(Contants.FACEBOOK_URI_HEAD_RES + getPackageName() + "/" + mResources.get(i));
            draweeView.setImageURI(uri);
            mViews.add(draweeView);
        }
    }

    public void setAdapter() {
        if (mGuidePageAdapter == null) {
            mGuidePageAdapter = new GuidePageAdapter(mViews);
        }
        mViewPager.setAdapter(mGuidePageAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mHintText.setText(mGuideHintContent[position]);
                if (position == mGuideHintContent.length - 1) {
                    mEnterText.setVisibility(View.VISIBLE);
                    startAnimationFadeIn(mEnterText);
                } else {
                    mEnterText.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 渐变动画 显示
     *
     * @param view
     */
    public void startAnimationFadeIn(View view) {
        Animation a = new AlphaAnimation(0.3f, 1.0f);
        a.setDuration(500);
        view.startAnimation(a);
    }

    /**
     * 渐变动画 隐藏
     *
     * @param view
     */
    public void startAnimationFadeOut(View view) {
        Animation a = new AlphaAnimation(1.0f, 0.3f);
        a.setDuration(500);
        view.startAnimation(a);
    }

    @Override
    public void onClick(View v) {
        OSPUtils.setFirstLauncher(GuidePageActivity.this, false);
        JumpManager.jumpHomeActivity(GuidePageActivity.this);
        GuidePageActivity.this.finish();
    }
}
