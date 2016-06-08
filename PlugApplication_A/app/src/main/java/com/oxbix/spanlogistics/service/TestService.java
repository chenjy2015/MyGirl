package com.oxbix.spanlogistics.service;

import android.content.Intent;

import com.ryg.dynamicload.DLBasePluginService;
import com.ryg.dynamicload.internal.DLIntent;
import com.ryg.dynamicload.listenner.PlugUpdateListenner;

public class TestService extends DLBasePluginService {
    public static final String action = "app.originality.com.pulgmainapplication.update";
    private PlugUpdateListenner mPlugUpdateListenner;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        mPlugUpdateListenner = (PlugUpdateListenner) intent.getSerializableExtra("PlugUpdateListenner");
        doSomething();
        return super.onStartCommand(intent, flags, startId);
    }


    public void doSomething() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DLIntent intent = new DLIntent();
                intent.setAction(action);
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    intent.putExtra("progress", "" + (i+1));
                    that.sendBroadcast(intent);
                }
            }
        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 100; i++) {
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    mPlugUpdateListenner.onUpdate(i + 1);
//                }
//            }
//        }).start();
    }
}