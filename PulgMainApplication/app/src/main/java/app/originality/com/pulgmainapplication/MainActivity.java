package app.originality.com.pulgmainapplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Environment;
import android.os.Looper;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import com.ryg.dynamicload.internal.DLIntent;
import com.ryg.dynamicload.internal.DLPluginManager;
import com.ryg.dynamicload.listenner.PlugUpdateListenner;
import com.ryg.utils.DLUtils;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import app.originality.com.pulgmainapplication.bean.PluginItem;

/**
 * @author cjy
 * @version V1.0
 * @company 跨越速运
 * @Description 插件化程序 主程序
 * @date 2016/5/28
 */
public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private TextView mContentTextView;
    private Button mStartService;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<PluginItem> mPluginItems = new ArrayList<PluginItem>();
    private ServiceConnection mConnection;
    private MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStartService = (Button) this.findViewById(R.id.id_start_service);
        mContentTextView = (TextView) this.findViewById(R.id.id_content);
        mListView = (ListView) this.findViewById(R.id.listview);
        mListView.setOnItemClickListener(this);
        initData();
        initEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.setPriority(1000);
        intentFilter.addAction(action);
        myBroadcastReceiver = new MyBroadcastReceiver();
        this.registerReceiver(myBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myBroadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (mConnection != null) {
            this.unbindService(mConnection);
        }
    }

    private void initEvent() {
        mStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPluginItems != null && mPluginItems.size() > 0) {
                    PluginItem item = mPluginItems.get(0);
                    DLPluginManager pluginManager = DLPluginManager.getInstance(MainActivity.this);
                    if (item.launcherServiceName != null) {
                        DLIntent intent = new DLIntent(item.packageInfo.packageName, item.launcherServiceName);
//                        intent.putExtra("PlugUpdateListenner", new OnUpdateListenner());
                        pluginManager.startPluginService(MainActivity.this, intent);
                    }
                }
            }
        });
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String pluginFolder = Environment.getExternalStorageDirectory() + "/DynamicLoadHost";
                File file = new File(pluginFolder);
                File[] plugins = file.listFiles();

                if (plugins == null || plugins.length == 0) {
                    mHanlder.sendEmptyMessage(0);
                    return;
                }
                for (File plugin : plugins) {
                    PluginItem item = new PluginItem();
                    item.pluginPath = plugin.getAbsolutePath();
                    item.packageInfo = DLUtils.getPackageInfo(MainActivity.this, item.pluginPath);
                    if (item.packageInfo.activities != null && item.packageInfo.activities.length > 0) {
                        item.launcherActivityName = item.packageInfo.activities[0].name;
                    }
                    if (item.packageInfo.services != null && item.packageInfo.services.length > 0) {
                        item.launcherServiceName = item.packageInfo.services[0].name;
                    }
                    mPluginItems.add(item);
                    DLPluginManager.getInstance(MainActivity.this).loadApk(item.pluginPath);
                }
                Looper.prepare();
                mHanlder.sendEmptyMessage(2);
                Looper.loop();
            }
        }).start();

    }

    private Handler mHanlder = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0) {
                Toast.makeText(getApplicationContext(), "DynamicLoadHost 文件下没有对应的APK文件", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 1) {
                mContentTextView.setText(msg.arg1 + "%");
            } else if (msg.what == 2) {
                if (mPluginItems != null && mPluginItems.size() > 0) {
                    setAdapter();
                }
            }
        }
    };

    public void setAdapter() {
        String[] pluginItems = new String[mPluginItems.size()];
        for (int i = 0; i < mPluginItems.size(); i++) {
            pluginItems[i] = mPluginItems.get(i).launcherActivityName;
        }
        mAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.test_list_item, android.R.id.text1, pluginItems);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        PluginItem item = mPluginItems.get(position);
        DLPluginManager pluginManager = DLPluginManager.getInstance(this);
        DLIntent dlIntent = new DLIntent(item.packageInfo.packageName, item.launcherActivityName);
//        dlIntent.putExtra("PlugUpdateListenner", new OnUpdateListenner());
        dlIntent.putExtra("text", "主程序调起!");
        pluginManager.startPluginActivityForResult(this, dlIntent, 0);

        //如果存在Service则调用起Service
//        if (item.launcherServiceName != null) {
        //startService
//            DLIntent intent = new DLIntent(item.packageInfo.packageName, item.launcherServiceName);
        //startService
//	        pluginManager.startPluginService(this, intent);

        //bindService
//	        pluginManager.bindPluginService(this, intent, mConnection = new ServiceConnection() {
//                public void onServiceDisconnected(ComponentName name) {
//                }
//
//                public void onServiceConnected(ComponentName name, IBinder binder) {
//                    int sum = ((ITestServiceInterface)binder).sum(5, 5);
//                    Log.e("MainActivity", "onServiceConnected sum(5 + 5) = " + sum);
//                }
//            }, Context.BIND_AUTO_CREATE);
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            String content = data.getStringExtra("content");
            if (content != null) {
                mContentTextView.setText(content);
            }
        } catch (Exception e) {

        }
    }


    public static final String action = "app.originality.com.pulgmainapplication.update";

    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(action)) {
                mContentTextView.setText(intent.getStringExtra("progress") + "%");
            }
        }
    }

    public class OnUpdateListenner implements PlugUpdateListenner, Serializable {

        @Override
        public void onUpdate(int progress) {
            Message msg = Message.obtain();
            msg.what = 1;
            msg.arg1 = progress;
            mHanlder.sendMessage(msg);
        }
    }
}
