package com.umeng.share.UmengSharedApplication.application;

import android.app.Application;

import com.umeng.share.UmengSharedApplication.util.ShareHelper;

public class SharedApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ShareHelper.init();
    }
}