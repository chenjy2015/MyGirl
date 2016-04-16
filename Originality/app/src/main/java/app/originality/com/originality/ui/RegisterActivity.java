package app.originality.com.originality.ui;

import android.os.Bundle;

import app.originality.com.originality.R;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initEvent();
    }

    @Override
    protected int setView() {
        return R.layout.activity_register;
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

    }
}
