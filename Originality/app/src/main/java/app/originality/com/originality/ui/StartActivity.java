package app.originality.com.originality.ui;

import android.os.Bundle;
import android.os.Handler;

import app.originality.com.originality.R;
import app.originality.com.originality.application.OApplication;
import app.originality.com.originality.storage.OSPUtils;
import app.originality.com.originality.util.JumpManager;

/**
 * @author cjy
 * @version V1.0
 * @company 创意APP
 * @Description 启动页
 * @date 2016/4/1
 */
public class StartActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        loadData();
    }

    @Override
    protected int setView() {
        return R.layout.activity_start;
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {
        mTitleBar.hideTitleLayout();
    }

    @Override
    protected void loadData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //首次启动 进入引导界面
//                if (OSPUtils.getFirstLauncher(StartActivity.this)) {
                    JumpManager.jumpGuidePageActivity(StartActivity.this);
                    //非首次启动 判断用户是否为空 否则进入登录界面
//                } else if (OApplication.getUserInfor() == null) {
//                    JumpManager.jumpLoginActivity(StartActivity.this);
//                } else {
//                    JumpManager.jumpHomeActivity(StartActivity.this);
//                }
                StartActivity.this.finish();
            }
        }, 1000);
    }
}
