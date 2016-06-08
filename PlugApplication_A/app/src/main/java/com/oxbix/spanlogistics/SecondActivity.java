package com.oxbix.spanlogistics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ryg.dynamicload.DLBasePluginActivity;
import com.ryg.dynamicload.internal.DLIntent;

public class SecondActivity extends DLBasePluginActivity {

    private TextView mTextView;
    private Button mBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findView();
        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        if (text != null) {
            mTextView.setText(text);
        }
    }

    public void findView() {
        mTextView = (TextView) that.findViewById(R.id.id_textview);
        mBtn = (Button) that.findViewById(R.id.id_btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLIntent dlIntent = new DLIntent();
                dlIntent.putExtra("content", "子程序 " + that.getPackageName() + "回调");
                that.setResult(0, dlIntent);
                that.finish();
            }
        });
    }
}
