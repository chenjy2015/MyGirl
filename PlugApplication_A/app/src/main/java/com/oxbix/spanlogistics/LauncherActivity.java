package com.oxbix.spanlogistics;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ryg.dynamicload.DLBasePluginActivity;
import com.ryg.dynamicload.internal.DLIntent;
import com.ryg.dynamicload.listenner.PlugUpdateListenner;

import com.oxbix.spanlogistics.R;

import java.io.Serializable;


/**
 * @author cjy
 * @version V1.0
 * @company
 * @Description 插件程序 A
 * @date 2016/5/26
 */
public class LauncherActivity extends DLBasePluginActivity {

    private TextView mTextView;
    private Button mBtn, mStopBtn;
    private PlugUpdateListenner mPlugUpdateListenner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        init();
    }


    public void findView() {
        mTextView = (TextView) that.findViewById(R.id.id_textview);
        mBtn = (Button) that.findViewById(R.id.id_btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLIntent intent = new DLIntent(getPackageName(), SecondActivity.class);
                intent.putExtra("text", "from DL framework");
                startPluginActivityForResult(intent, 0);
            }
        });

        mStopBtn = (Button) that.findViewById(R.id.id_stop_btn);
        mStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLIntent dlIntent = new DLIntent();
                dlIntent.putExtra("content", "子程序 " + that.getPackageName() + "回调");
                that.setResult(0, dlIntent);
                that.finish();

            }
        });
    }

    public void init() {
        String content = getIntent().getStringExtra("text");
        mTextView.setText(content);
        mPlugUpdateListenner = (PlugUpdateListenner) getIntent().getSerializableExtra("PlugUpdateListenner");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String content = data.getStringExtra("content");
        mTextView.setText(content == null ? "子程序回传值为空!" : content);
    }

}
