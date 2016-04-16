package app.originality.com.originality.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import app.originality.com.originality.ui.BaseActivity;


/**
 * @author yzw
 * @version V1.0
 * @company 跨越速运
 * @Description Fragment基类
 * @date 2015/12/7
 */
public abstract class BaseFragment extends Fragment {

    protected Activity mAct;

    private View mParent;

    public BaseFragment() {
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mAct = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mParent = View.inflate(mAct, setView(), null);
        return mParent;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        preCreate();
        super.onActivityCreated(savedInstanceState);
        findViews();
        initEvent();
        init();
        loadData();
    }

    public void preCreate() {

    }

    protected View findViewById(int viewId) {
        return mParent.findViewById(viewId);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        return false;
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
}
