package com.oxbix.spanlogistics.service;

import com.ryg.dynamicload.DLBasePluginService;
import com.ryg.dynamicload.internal.DLIntent;


public class TestService extends DLBasePluginService {
    public static final String action = "app.originality.com.pulgmainapplication.update";

    @Override
    public void onCreate() {
        super.onCreate();

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
    }
}