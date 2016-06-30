package app.originality.com.originality.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import app.originality.com.originality.R;
import app.originality.com.originality.bean.UserInfor;
import app.originality.com.originality.util.LoginHelper;
import app.originality.com.originality.util.SystemBarTintManager;
import app.originality.com.originality.widget.TitleBar;


/**
 * @author cjy
 * @version V1.0
 * @company Chenjy_Studio
 * @Description Activity基类
 * @date 2016/4/1
 */
public abstract class BaseActivity extends Activity {


    /**
     * TitleBar
     */
    protected TitleBar mTitleBar;
    /**
     * 内容容器
     */
    private FrameLayout mainContainer;

    /**
     * 4.4版本以上的沉浸式
     */
    protected SystemBarTintManager mTintManager;

    /**
     * 在oncreate之前调用此方法
     */
    protected void preCreate() {
        //        Window win = getWindow();
        //        win.addFlags(WindowManager.LayoutParams.FLAG_SECURE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preCreate();
        super.onCreate(savedInstanceState);
        View view = View.inflate(getApplicationContext(), R.layout.activity_base, null);
        // 布局内容会从actionbar以下开始
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            view.setFitsSystemWindows(true);
        }
        setContentView(view);
        initContentView();
        findViews();
    }

    /**
     * 设置activity的布局文件
     *
     * @return 布局文件的resId
     */
    protected abstract int setView();

    /**
     * 获取界面元素
     */
    protected abstract void findViews();

    /**
     * 设置事件
     */
    protected abstract void initEvent();

    /**
     * 对象的初始化工作
     */
    protected abstract void init();

    /**
     * 加载数据
     */
    protected abstract void loadData();


    /**
     * 初始化布局文件
     */
    private void initContentView() {

        mTitleBar = (TitleBar) findViewById(R.id.title_bar);
        mTitleBar.setLeftImgBackground();
        mTitleBar.setLeftLayoutClickListenner(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                BaseActivity.this.finish();
            }
        });
        mainContainer = (FrameLayout) findViewById(R.id.activity_base_content);
        LayoutInflater.from(this).inflate(setView(), mainContainer);

        mTintManager = new SystemBarTintManager(this);
        if (setupStatusBar()) {
            setStatusBarState();
        }
    }


    /**
     * (是否设置状态栏.)
     * <h3>Version</h3> 1.0
     * <h3>CreateTime</h3> 2016/3/22,11:15
     * <h3>UpdateTime</h3> 2016/3/22,11:15
     * <h3>CreateAuthor</h3> （Geoff）
     * <h3>UpdateAuthor</h3>
     * <h3>UpdateInfo</h3> (此处输入修改内容,若无修改可不写.)
     *
     * @return
     */
    protected boolean setupStatusBar() {
        return true;
    }

    /**
     * (设置状态栏状态.)
     * <h3>Version</h3> 1.0
     * <h3>CreateTime</h3> 2016/3/22,11:16
     * <h3>UpdateTime</h3> 2016/3/22,11:16
     * <h3>CreateAuthor</h3> （Geoff）
     * <h3>UpdateAuthor</h3>
     * <h3>UpdateInfo</h3> (此处输入修改内容,若无修改可不写.)
     */
    private void setStatusBarState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            mTintManager = new SystemBarTintManager(this);
            mTintManager.setStatusBarTintEnabled(true);
            // 使StatusBarTintView 和 actionbar的颜色保持一致，风格统一。
            mTintManager.setStatusBarTintResource(R.color.title_layout_color);
            // 设置状态栏的文字颜色
            mTintManager.setStatusBarDarkMode(false, this);
        }
    }

    /**
     * (设置状态栏背景状态.)
     * <h3>Version</h3> 1.0
     * <h3>CreateTime</h3> 2016/3/22,11:16
     * <h3>UpdateTime</h3> 2016/3/22,11:16
     * <h3>CreateAuthor</h3> （Geoff）
     * <h3>UpdateAuthor</h3>
     * <h3>UpdateInfo</h3> (此处输入修改内容,若无修改可不写.)
     *
     * @param on
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public UserInfor getUserInfor() {
        return new LoginHelper().getUserInfor(this);
    }

    /**
     * 设置用户信息
     *
     * @param userInfor
     */
    public void setUserInfor(UserInfor userInfor) {
        new LoginHelper().setUserInfor(this, userInfor);
    }
}